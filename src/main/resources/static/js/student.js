/**
 * Created by Rico.Chen on 2017/2/6.
 */

$(document).ready(function () {

    function checkboxSelectedRight() {
        var i = 0;
        var _id = 0;
        $('input[type="checkbox"]').each(function () {
            // jQuery 1.6+ attr change prop
            if($(this).prop('checked')) {
                i++;
                _id = $(this).prop('id');
            };
        });
        if(i == 0) {
            alert("You must selected one at least.");
            return -1;
        }

        if(i > 1) {
            alert("You should be selected only one.");
            return -1;
        }

        return _id;
    }


    $('#btnModify').bind('click',function () {
        var _id = checkboxSelectedRight();

        //alert(_id);

        if(_id == -1) {
            return;
        }



        $(location).prop('href', 'student/modify/' + _id);

    });

    $('#btnRemove').bind('click',function () {

        var _id = checkboxSelectedRight();

        //alert(_id);

        if(_id == -1) {
            return;
        }

       $.ajax({
            type: 'GET',
            url: '/student/remove/' + _id,
            success: function (data) {
                alert(data);
                $(location).prop('href', '/student');
            },
            error: function(data,status,er) {
               alert("error: "+data+" status: "+status+" er:"+er);
            }
       });

    });

    $('#btnSingle').bind('click', function () {

        var _id = checkboxSelectedRight();

        //alert(_id);

        if(_id == -1) {
            return;
        }

        $.ajax({
            type: 'GET',
            url: '/student/ajaxStudentById',
            data: "id=" + _id,
            success: function (data) {
                alert(data.id + "\t" + data.name + "\t" + data.age);
                //alert(data);
            },
            error: function(data,status,er) {
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });


    });

    $('#btnList').bind('click', function () {
        $.ajax({
            type: 'GET',
            url: '/student/ajaxStudentList',
            success: function (data) {
                $.each(data, function (index, value) {
                    alert(value.id + value.name + value.age);
                })
            },
            error: function(data,status,er) {
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });
    });

   /* $('.pager').bind('click', function () {
        alert($(this).prop('id'));
        //alert($("#divPagerNO").html());
        var _pagerNO = $("#divPagerNO").html();
        if($(this).prop('id') == 'btnNext') {
            _pagerNO = (_pagerNO-0) + 1;
        } else if($(this).prop('id') == 'btnPre'){
            alert('pre');
            _pagerNO = (_pagerNO-0) - 1;
        } else if($(this).prop('id') == 'btnFirst') {
            _pagerNO = 1;
        } else {
            _pagerNO =$('#divPagerCount').html();
        }
        alert(_pagerNO);
        $.ajax({
            type: 'GET',
            url: '/student?limit='+ _pagerNO,
            success: function (data) {
                //$(location).prop('href', '/student');
            },
            error: function(data,status,er) {
                alert("error: "+data+" status: "+status+" er:"+er);
            },
            dataType: 'html'
        });
    });*/

});
