/*
 *
 * jqTransform
 * by mathieu vilaplana mvilaplana@dfc-e.com
 * Designer ghyslain armand garmand@dfc-e.com
 *
 *
 * Version 1.0 25.09.08
 * Version 1.1 06.08.09
 * Add event click on Checkbox and Radio
 * Auto calculate the size of a select element
 * Can now, disabled the elements
 * Correct bug in ff if click on select (overflow=hidden)
 * No need any more preloading !!
 *
 ******************************************** */

(function($){
	var defaultOptions = {preloadImg:true};
	var jqTransformImgPreloaded = false;

	var jqTransformPreloadHoverFocusImg = function(strImgUrl) {
		//guillemets to remove for ie
		strImgUrl = strImgUrl.replace(/^url\((.*)\)/,'$1').replace(/^\"(.*)\"$/,'$1');
		var imgHover = new Image();
		imgHover.src = strImgUrl.replace(/\.([a-zA-Z]*)$/,'-hover.$1');
		var imgFocus = new Image();
		imgFocus.src = strImgUrl.replace(/\.([a-zA-Z]*)$/,'-focus.$1');
	};


	/***************************
	  Labels
	***************************/
	var jqTransformGetLabel = function(objfield){
		var selfForm = $(objfield.get(0).form);
		var oLabel = objfield.next();
		if(!oLabel.is('label')) {
			oLabel = objfield.prev();
			if(oLabel.is('label')){
				var inputname = objfield.attr('id');
				if(inputname){
					oLabel = selfForm.find('label[for="'+inputname+'"]');
				}
			}
		}
		if(oLabel.is('label')){return oLabel.css('cursor','pointer');}
		return false;
	};

	/* Hide all open selects */
	var jqTransformHideSelect = function(oTarget){
		var ulVisible = $('.jqTransformSelectWrapper ul:visible');
		ulVisible.each(function(){
			var oSelect = $(this).parents(".jqTransformSelectWrapper:first").find("select").get(0);
			//do not hide if click on the label object associated to the select
			if( !(oTarget && oSelect.oLabel && oSelect.oLabel.get(0) == oTarget.get(0)) ){$(this).hide();}
		});
	};
	/* Check for an external click */
	var jqTransformCheckExternalClick = function(event) {
		if ($(event.target).parents('.jqTransformSelectWrapper').length === 0) { jqTransformHideSelect($(event.target)); }
	};

	/* Apply document listener */
	var jqTransformAddDocumentListener = function (){
		$(document).mousedown(jqTransformCheckExternalClick);
	};

	/* Add a new handler for the reset action */
	var jqTransformReset = function(f){
		var sel;
		$('.jqTransformSelectWrapper select', f).each(function(){sel = (this.selectedIndex<0) ? 0 : this.selectedIndex; $('ul', $(this).parent()).each(function(){$('a:eq('+ sel +')', this).click();});});
		$('a.jqTransformCheckbox, a.jqTransformRadio', f).removeClass('jqTransformChecked');
		$('input:checkbox, input:radio', f).each(function(){if(this.checked){$('a', $(this).parent()).addClass('jqTransformChecked');}});
	};

	/***************************
	  Buttons
	 ***************************/
	$.fn.jqTransInputButton = function(){
		return this.each(function(){
			var newBtn = $('<button id="'+ this.id +'" name="'+ this.name +'" type="'+ this.type +'" class="'+ this.className +' jqTransformButton"><span><span>'+ $(this).attr('value') +'</span></span>')
				.hover(function(){newBtn.addClass('jqTransformButton_hover');},function(){newBtn.removeClass('jqTransformButton_hover')})
				//.mousedown(function(){newBtn.addClass('jqTransformButton_click')})
				//.mouseup(function(){newBtn.removeClass('jqTransformButton_click')})
			;
			$(this).replaceWith(newBtn);
		});
	};

	/***************************
	  Text Fields
	 ***************************/
	$.fn.jqTransInputText = function(){
		return this.each(function(){
			var $input = $(this);

			if($input.hasClass('jqtranformdone') || !$input.is('input')) {return;}
			$input.addClass('jqtranformdone');

			var oLabel = jqTransformGetLabel($(this));
			oLabel && oLabel.bind('click',function(){$input.focus();});

			var inputSize=$input.width();
			if($input.attr('size')){
				inputSize = $input.attr('size')*10;
				$input.css('width',inputSize);
			}

			$input.addClass("jqTransformInput").wrap('<div class="jqTransformInputWrapper"><div class="jqTransformInputInner"><div></div></div></div>');
			var $wrapper = $input.parent().parent().parent();
			$wrapper.css("width", inputSize+10);
			$input
				.focus(function(){$wrapper.addClass("jqTransformInputWrapper_focus");})
				.blur(function(){$wrapper.removeClass("jqTransformInputWrapper_focus");})
				.hover(function(){$wrapper.addClass("jqTransformInputWrapper_hover");},function(){$wrapper.removeClass("jqTransformInputWrapper_hover");})
			;

			/* If this is safari we need to add an extra class */
			$.browser.safari && $wrapper.addClass('jqTransformSafari');
			$.browser.safari && $input.css('width',$wrapper.width()+16);
			this.wrapper = $wrapper;

		});
	};

	/***************************
	  Check Boxes
	 ***************************/
	$.fn.jqTransCheckBox = function(options){
		var opts = $.extend({}, {defaultValue:null}, options);
		return this.each(function(){
			 var $checkbox = $(this);
			if($(this).hasClass('jqTransformHidden') ) {
				if(opts.defaultValue) this.checked && $checkbox.prev().addClass('jqTransformChecked');
				return false;
			}


			var inputSelf = this;

			//set the click on the label
			var oLabel=jqTransformGetLabel($checkbox);
			oLabel && oLabel.click(function(){aLink.trigger('click');});

			var aLink = $('<a href="#" class="jqTransformCheckbox" checkbox-value="'+this.value+'" checkbox-name="'+this.name+'"></a>');
			//wrap and add the link
			$checkbox.addClass('jqTransformHidden').wrap('<span class="jqTransformCheckboxWrapper"></span>').parent().prepend(aLink);
			//on change, change the class of the link
			$checkbox.change(function(){
				this.checked && aLink.addClass('jqTransformChecked') || aLink.removeClass('jqTransformChecked');
				return true;
			});
			// Click Handler, trigger the click and change event on the input
			aLink.click(function(){
				//do nothing if the original input is disabled
				if($checkbox.attr('disabled')){return false;}
				//trigger the envents on the input object
				$checkbox.trigger('click').trigger("change");
				return false;
			});

			// set the default state
			this.checked && aLink.addClass('jqTransformChecked');

		});
	};
	$.fn.jqTransCheckBoxClick = function(checkboxClick){
		return this.each(function(){
			var $this = $(this);
			$this.prev().bind("click", function(){
				if(checkboxClick && typeof checkboxClick == "function")
				{
					checkboxClick($this);
				}
			});
		});
	};
	/***************************
	  Radio Buttons
	 ***************************/
	$.fn.jqTransRadio = function(options){
		var opts = $.extend({}, {defaultValue:null}, options);
		return this.each(function(){
			if($(this).hasClass('jqTransformHidden') ) {
				if(opts.defaultValue) $("[radio-value="+opts.defaultValue+"]").click();
				return false;
			}

			var $input = $(this);
			var inputSelf = this;

			oLabel = jqTransformGetLabel($input);
			oLabel && oLabel.click(function(){aLink.trigger('click');});

			var aLink = $('<a href="#" class="jqTransformRadio" radio-name="'+ this.name +'" radio-value="'+this.value+'"></a>');
			$input.addClass('jqTransformHidden').wrap('<span class="jqTransformRadioWrapper"></span>').parent().prepend(aLink);

			$input.change(function(){
				inputSelf.checked && aLink.addClass('jqTransformChecked') || aLink.removeClass('jqTransformChecked');
				return true;
			});
			// Click Handler
			aLink.click(function(){
				if($input.attr('disabled')){return false;}
				$input.trigger('click').trigger('change');
				$(this).parent("span.jqTransformRadioWrapper").siblings("label").removeClass("blue pie");
				$(this).parent("span.jqTransformRadioWrapper").next("label").addClass("blue pie");

				// uncheck all others of same name input radio elements
				$('input[name="'+$input.attr('name')+'"]',inputSelf.form).not($input).each(function(){
					$(this).attr('type')=='radio' && $(this).trigger('change');
				});

				return false;
			});
			inputSelf.checked && aLink.addClass('jqTransformChecked');
			inputSelf.checked && $(this).parent("span.jqTransformRadioWrapper").next("label").addClass("blue pie");
			// set the default state
			if(!opts.defaultValue)
				inputSelf.checked && aLink.addClass('jqTransformChecked');
			else
				$("[radio-value="+opts.defaultValue+"]").click();
		});
	};
	$.fn.jqTransRadioClick = function(radioClick){
		return this.each(function(){
			var $this = $(this);
			$this.prev().bind("click", function(){
				if(radioClick && typeof radioClick == "function")
				{
					radioClick($this);
				}
			});
		});
	};
	/***************************
	  TextArea
	 ***************************/
	$.fn.jqTransTextarea = function(){
		return this.each(function(){
			var textarea = $(this);

			if(textarea.hasClass('jqtransformdone')) {return;}
			textarea.addClass('jqtransformdone');

			oLabel = jqTransformGetLabel(textarea);
			oLabel && oLabel.click(function(){textarea.focus();});

			var strTable = '<table cellspacing="0" cellpadding="0" border="0" class="jqTransformTextarea">';
			strTable +='<tr><td id="jqTransformTextarea-tl"></td><td id="jqTransformTextarea-tm"></td><td id="jqTransformTextarea-tr"></td></tr>';
			strTable +='<tr><td id="jqTransformTextarea-ml">&nbsp;</td><td id="jqTransformTextarea-mm"><div></div></td><td id="jqTransformTextarea-mr">&nbsp;</td></tr>';
			strTable +='<tr><td id="jqTransformTextarea-bl"></td><td id="jqTransformTextarea-bm"></td><td id="jqTransformTextarea-br"></td></tr>';
			strTable +='</table>';
			var oTable = $(strTable)
					.insertAfter(textarea)
					.hover(function(){
						!oTable.hasClass('jqTransformTextarea-focus') && oTable.addClass('jqTransformTextarea-hover');
					},function(){
						oTable.removeClass('jqTransformTextarea-hover');
					})
				;

			textarea
				.focus(function(){oTable.removeClass('jqTransformTextarea-hover').addClass('jqTransformTextarea-focus');})
				.blur(function(){oTable.removeClass('jqTransformTextarea-focus');})
				.appendTo($('#jqTransformTextarea-mm div',oTable))
			;
			this.oTable = oTable;
			if($.browser.safari){
				$('#jqTransformTextarea-mm',oTable)
					.addClass('jqTransformSafariTextarea')
					.find('div')
						.css('height',textarea.height())
						.css('width',textarea.width())
				;
			}
		});
	};

	/***************************
	  Select
	 ***************************/
	$.fn.jqTransSelect = function(opts){
		return this.each(function(index){
			var $select = $(this),
			 $ul,$wrapper,oLabel;

			if($select.attr('multiple')) {return;}
			if($select.hasClass('jqTransformHidden')) {
				$wrapper = $select.parent();
				$ul = $select.prev();
				oLabel = $wrapper.prev();
				$ul.css({"height":"150","overflow-x":"hidden","overflow-y":"auto"});
			}
			else
			{
				oLabel  =  jqTransformGetLabel($select);
				/* First thing we do is Wrap it */
				$wrapper = $select
					.addClass('jqTransformHidden')
					.wrap('<div class="jqTransformSelectWrapper"></div>')
					.parent()
					.css({zIndex: 10-index})
				;

				/* Now add the html for the select */
				$wrapper.prepend('<div><span></span><a href="#" class="jqTransformSelectOpen"></a></div><ul></ul>');
				$ul = $('ul', $wrapper).css('width',$select.width()).hide();
				}


				/* Now we add the options */
				$('option', this).each(function(i){
					var oLi = $('<li><a href="#" index="'+ i +'" option-value="'+$(this).attr("value")+'">'+ $(this).html() +'</a></li>');
					$ul.append(oLi);
			});
			$wrapper.mouseleave(function(){
				$(this).find("ul").hide();
			});
			/* Add click handler to the a */
			$ul.find('a').click(function(){
					$('a.selected', $wrapper).removeClass('selected');
					$(this).addClass('selected');
					/* Fire the onchange event */
					if ($select[0].selectedIndex != $(this).attr('index') && $select[0].onchange) { $select[0].selectedIndex = $(this).attr('index'); $select[0].onchange(); }
					$select[0].selectedIndex = $(this).attr('index');
					$('span:eq(0)', $wrapper).html($(this).html());
					$ul.hide();
					if(opts && opts.onchange && typeof opts.onchange == 'function') opts.onchange($(this));
					//$.fn.jqTransSelect.selectChange($(this));
					return false;
			});
			/* Set the default */
			if(opts && opts.defaultValue)
				$('a[option-value='+opts.defaultValue+']', $ul).click();
			else
				$('a:eq('+ this.selectedIndex +')', $ul).click();

			$('span:first', $wrapper).click(function(){$("a.jqTransformSelectOpen",$wrapper).trigger('click');});
			oLabel && oLabel.click(function(){$("a.jqTransformSelectOpen",$wrapper).trigger('click');});
			this.oLabel = oLabel;

			/* Apply the click handler to the Open */
			var oLinkOpen = $('a.jqTransformSelectOpen', $wrapper)
				.click(function(){
					if($select.attr('disabled')){return false;}
					$ul.show();
					return false;
				});

			// Set the new width,default 155,use sel-width property defined width
			var oSpan = $('span:first',$wrapper);
			_setSelectWidth($wrapper,$select, $ul, oSpan);

			// Calculate the height if necessary, less elements that the default height
			//show the ul to calculate the block, if ul is not displayed li height value is 0
			$ul.css({display:'block',visibility:'hidden'});
			var iSelectHeight = ($('li',$ul).length)*($('li:first',$ul).height());//+1 else bug ff
			(iSelectHeight < $ul.height()) && $ul.css({height:iSelectHeight,'overflow':'hidden'});//hidden else bug with ff
			$ul.css({display:'none',visibility:'visible'});

		});
	};
	function _setSelectWidth($wrapper,$select, $ul, oSpan)
	{
		var selWidth = 155;	//设置默认宽度
		if($select.attr("sel-width")) selWidth = parseInt($select.attr("sel-width"));
		$wrapper.css('width',selWidth+"px");
		$ul.css({'width':(selWidth-2)+"px"});
		oSpan.css({width:(selWidth-25)+"px"});
	}
	/***************************
	  Dynamic Select
	 ***************************/
	 $.fn.jqTransDynamicSelect = function(data, options){ //data为数组对象
		 var options = $.extend({},{defaultValue:null, all:"",onchange:null},options);
		 return this.each(function(index){
			 var $select = $(this);
			 var $ul = $select.prev();
			 $select.html("");
			if(options.all)
			{
				$select.append('<option value="0">'+options.all+'</option>');
			}

			for(var i=0; i<data.length; i++)
			{
				$select.append('<option value="'+data[i].value+'">'+data[i].text+'</option>');
			}

			$select.jqTransSelect(options);
		});
	};
	 /*******************************
	  select change事件
	 *********************************/
	 $.fn.jqTransSelectChange = function(changeEvent){
		 return this.each(function(){
			var $this = $(this);
			$this.prev().find("a").bind("click", function(){
				if(changeEvent && typeof changeEvent == 'function')
				{
					changeEvent($(this));
				}
			});
		});

	 };
	 //$.fn.jqTransSelect.selectChange = function(curA){};
	/***************************
	  select 获取下拉列表的value值
	 ***************************/
	 $.fn.getSelectedValue = function(){
		 return $(this).prev().find("a.selected").attr("option-value");
	};
	/***************************
	  Select  获取下拉列表的文本值
	 ***************************/
	$.fn.getSelecedtText = function(){
		 return $(this).prev().find("a.selected").text();
	};

	$.fn.jqTransform = function(options){
		var opt = $.extend({},defaultOptions,options);

		/* each form */
		 return this.each(function(){
			var selfForm = $(this);
			if(selfForm.hasClass('jqtransformdone')) {return;}
			selfForm.addClass('jqtransformdone');

			$('input:submit, input:reset, input[type="button"]', this).jqTransInputButton();
			$('input:text, input:password', this).jqTransInputText();
			$('input:checkbox', this).jqTransCheckBox();
			$('input:radio', this).jqTransRadio();
			//$('textarea', this).jqTransTextarea();

			if( $('select', this).jqTransSelect().length > 0 ){jqTransformAddDocumentListener();}
			selfForm.bind('reset',function(){var action = function(){jqTransformReset(this);}; window.setTimeout(action, 10);});

			//preloading dont needed anymore since normal, focus and hover image are the same one
			/*if(opt.preloadImg && !jqTransformImgPreloaded){
				jqTransformImgPreloaded = true;
				var oInputText = $('input:text:first', selfForm);
				if(oInputText.length > 0){
					//pour ie on eleve les ""
					var strWrapperImgUrl = oInputText.get(0).wrapper.css('background-image');
					jqTransformPreloadHoverFocusImg(strWrapperImgUrl);
					var strInnerImgUrl = $('div.jqTransformInputInner',$(oInputText.get(0).wrapper)).css('background-image');
					jqTransformPreloadHoverFocusImg(strInnerImgUrl);
				}

				var oTextarea = $('textarea',selfForm);
				if(oTextarea.length > 0){
					var oTable = oTextarea.get(0).oTable;
					$('td',oTable).each(function(){
						var strImgBack = $(this).css('background-image');
						jqTransformPreloadHoverFocusImg(strImgBack);
					});
				}
			}*/


		}); /* End Form each */

	};/* End the Plugin */

})(jQuery);

