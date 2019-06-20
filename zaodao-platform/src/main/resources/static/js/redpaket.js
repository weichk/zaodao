// 打开红包
function award(countPoint,type) {
    layer.open({
        type: 1,
        title: false,
        area: ['500px', '364px'],
        closeBtn: 1,
        shadeClose: true,
        skin: 'popup_redpaket',
        content: '<div class="redpaket_wrap"><div class="fireworks">' +
        '<img src="/images/redpaket_fireworks.png" alt="" /></div>' +
        '<div class="opened_redpaket"><div class="content">' +
        '<div class="tit">恭喜您</div><div class="award">获得<span class="red">'+countPoint+'</span>积分</div>' +
        '</div></div></div>',
        success: function(layero, index) {
            // 烟花和奖品延迟显示，如果不需要延迟，可删除本段
            $('.popup_redpaket .fireworks').delay(200).fadeIn();
            $('.popup_redpaket .content').delay(500).fadeIn(100);
        },
        end: function () {
            if(type == 'cerditSignin') {
                location.reload();
            }

        }
    });
}

// 显示红包
function openRedpaket(countPoint,type) {
    layer.open({
        type: 1,
        title: false,
        area: ['500px', '364px'],
        closeBtn: 1,
        shadeClose: true,
        skin: 'popup_redpaket',
        content: '<div class="redpaket_wrap">' +
        '<a href="#" class="popup_open_redpaket">' +
        '<img src="/images/redpaket_closed.png" alt="开红包" /></div></div>',
        success: function(layero, index) {
            // 显示红包成功之后，点击打开红包并关闭显示红包层
            $('.popup_open_redpaket').click(function(e) {
                e.preventDefault();
                award(countPoint,type);
                layer.close(index);
            });
        }
    });
}