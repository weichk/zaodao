$(function () {
    // 设置当月开始的连续3个月份到左侧
    var today = new Date(),
        month = today.getMonth();

    $('.guide_calendar_nav a').each(function (i) {
        $('.guide_calendar_nav a').eq(i).html((month + i + 1) + "月");
    });
    today = null;
    month = null;
});

function updateLockedDay(dayIn, dayOut, isFirst) {
    if (isFirst == 0) { // 判断第一次写入时，对日历初始化
        $('#guide_calendar').calendar({
            checkIn: dayIn,
            checkOut: dayOut,
            lockDate: true,
            totalMohth: 3,
            isFirst: true,
            dayText: ['已约', '已约']
        });
    } else { // 大于第一次，不再对日历初始化
        $('#guide_calendar').calendar({
            checkIn: dayIn,
            checkOut: dayOut,
            lockDate: true,
            totalMohth: 3,
            isFirst: false,
            dayText: ['已约', '已约']
        });
    }
}
function initCalendar(lockedDay) {
    // 设定锁定日期
    for (var i = 0; i < lockedDay.length; i += i + 2) {
        var lockedIn = new Date(lockedDay[i]);
        var lockedOut = new Date(lockedDay[i + 1]);
        updateLockedDay(lockedIn, lockedOut, i);
    }
    // lockedIn = null;
    // lockedOut = null;
}
