$(function(){
    var ajax_link = $('.ajax_link');

    ajax_link.click(function(){
        $.get($(this).attr('href'),function(text){
            msgbbox(text);
            });
        return false;
    });
       
})
