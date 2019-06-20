$.fn.iVaryVal=function(iSet,CallBack){
    /*
     * Minus:点击元素--减小
     * Add:点击元素--增加
     * Input:表单元素
     * Min:表单的最小值，非负整数
     * Max:表单的最大值，正整数
     */
    iSet=$.extend({Minus:$('.J_minus'),Add:$('.J_add'),Input:$('.J_input'),Min:0,Max:20},iSet);
    var C=null,O=null;
    //插件返回值
    var $CB={};

    // 检查Input中的数值：值为最小时赋予减disable类；最大时赋予加disable类
    function disableAddorMinus() {
        iSet.Input.each(function(i){
            var val = iSet.Input.eq(i).val();
            if(val == iSet.Min) {
                iSet.Minus.addClass('disable');
                iSet.Add.removeClass('disable');
            }
            if(val == iSet.Max) {
                iSet.Add.addClass('disable');
                iSet.Minus.removeClass('disable');
            }
            if(val > iSet.Min && val < iSet.Max) {
                iSet.Minus.removeClass('disable');
                iSet.Add.removeClass('disable');
            }
        });
    }
    // 初始检查Input中的数值
    disableAddorMinus();

    //增加
    iSet.Add.each(function(i){
        $(this).click(function(){
            O=parseInt(iSet.Input.eq(i).val());
            (O+1<=iSet.Max) || (iSet.Max==null) ? iSet.Input.eq(i).val(O+1) : iSet.Input.eq(i).val(iSet.Max);
            //输出当前改变后的值
            $CB.val=iSet.Input.eq(i).val();
            $CB.index=i;
            //回调函数
            if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index);
            }
            // 增加时检查Input中的数值
            disableAddorMinus();
        });
    });
    //减少
    iSet.Minus.each(function(i){
        $(this).click(function(){
            O=parseInt(iSet.Input.eq(i).val());
            O-1<iSet.Min ? iSet.Input.eq(i).val(iSet.Min) : iSet.Input.eq(i).val(O-1);
            $CB.val=iSet.Input.eq(i).val();
            $CB.index=i;
            //回调函数
            if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index);
            }
            // 减少时检查Input中的数值
            disableAddorMinus();
        });
    });
    //手动
    iSet.Input.bind({
        'click':function(){
            O=parseInt($(this).val());
            $(this).select();
        },
        'keyup':function(e){
            if($(this).val()!=''){
                C=parseInt($(this).val());
                //非负整数判断
                if(/^[1-9]\d*|0$/.test(C)){
                    $(this).val(C);
                    O=C;
                }else{
                    $(this).val(O);
                }
            }
            //键盘控制：上右--加，下左--减
            if(e.keyCode==38 || e.keyCode==39){
                iSet.Add.eq(iSet.Input.index(this)).click();
            }
            if(e.keyCode==37 || e.keyCode==40){
                iSet.Minus.eq(iSet.Input.index(this)).click();
            }
            //输出当前改变后的值
            $CB.val=$(this).val();
            $CB.index=iSet.Input.index(this);
            //回调函数
            if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index);
            }
            // 手动输入keyup时检查Input中的数值
            disableAddorMinus();
        },
        'blur':function(){
            $(this).trigger('keyup');
            if($(this).val()==''){
                $(this).val(O);
            }
            //判断输入值是否超出最大最小值
            if(iSet.Max){
                if(O>=iSet.Max){
                    $(this).val(iSet.Max);
                }
            }
            if(O<=iSet.Min){
                $(this).val(iSet.Min);
            }

            //输出当前改变后的值
            $CB.val=$(this).val();
            $CB.index=iSet.Input.index(this);
            //回调函数
            if (typeof CallBack == 'function') {
                CallBack($CB.val,$CB.index);
            }
        }
    });
}

//调用
$( function() {

    // 点击人数框弹出增减下拉
    $('.person_num_head').click(function(){
        $('.person_selection').toggle();
        $('.person_num_head').toggleClass('active');
    });

    // 点击其它区域隐藏增减下拉
    $(document).bind("click", function(event) {
        if($(event.target).parents(".person_num").length==0 && $(event.target).parents(".person_selection").length==0) {
            $('.person_selection').hide();
            $('.person_num_head').removeClass('active');
        }
    });

    // 成人
    $('.set_num_1').iVaryVal({Minus:$('.J_minus_1'),Add:$('.J_add_1'),Input:$('.J_input_1')},function(value,index){
        $('#person_num_1').html(value);
    });

    // 儿童
    $('.set_num_2').iVaryVal({Minus:$('.J_minus_2'),Add:$('.J_add_2'),Input:$('.J_input_2')},function(value,index){
        $('#person_num_2').html(value);
    });

});
