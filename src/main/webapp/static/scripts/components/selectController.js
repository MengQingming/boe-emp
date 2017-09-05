/**
 * Created by 田野 on 2017/3/13.
 */
function select2Controller(node) {
    $('.select2-container').width($(node).width() + 12 + 'px');
}

$(document).ready(function () {
    $('#searchForm input[type="reset"]').click(function () {
        $('#searchForm .select2-container .select2-chosen').text("请选择");
    })
})