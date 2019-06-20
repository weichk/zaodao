$(function() {
    if(!document.getElementById('guide_calendar')) return false;
    var calendarBox = document.getElementById('guide_calendar');
    /*使用方式：
    new Kalendae(指定容器,配置);
    配置属性注解：
    months属性表示日历显示几个月，值：1、2、3.....；默认值：1
    mode属性表示显示的是单选、多选还是范围，值：'single'、'multiple'、'range'；默认值：'single'
    subscribe属性表示绑定kalendea指定的事件，支持的事件有change、date-clicked、view-changed
    */

    var classMap = ['2017-06-12','2017-06-13','2017-07-13'];
    for(var lockedDay=0; lockedDay<classMap.length; lockedDay++) {
        classMap[Kalendae.moment(classMap[lockedDay],'YYYY-MM-DD').format('YYYY-MM-DD')] = 'k-blackout';
    }
    var calendar = new Kalendae({
        attachTo: calendarBox,
        months: 3,
        mode: 'multiple',
        direction: 'today-future',
        dateClassMap: classMap,
        blackout: function (date) {
            var j = classMap.length;
            while(j--) {
                if (date.format('YYYY-MM-DD') == classMap[j]) {
                    return classMap[j];
                }
            }
        }
        // 设定默认选中的开始和结束日期(multiple模式下，多个日期以逗号分隔)
        // selected:'2017-06-02,2017-06-06,2017-06-07,2017-06-08,2017-06-21'
    });

    // 设置导航月份
    var today = new Date();
        thisMonth = today.getMonth() + 1;
    $('.ca_nav a').each(function(i) {
        $('.ca_nav a').eq(i).html(thisMonth + i + '月');
    });

});
