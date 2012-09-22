$(function(){

    var $body = $('body');
    var $html = $('html');

    var $info = $('.info');

    var timeout;

    var cancel_btn = $('.cancel');
    var cancel_modal_no = $('.cancel-modal-no');
    var cancel_modal_yes = $('.cancel-modal-yes');

    function show(text){

        msg = ('<div class="alert-message block-message warning fade in" data-alert="alert">'
            +'<a class="close" href="#">×</a>'
            +'<p><strong>'+ text + '</strong></p>'
            +'</div>')

        $info.html(msg);

		if (timeout) {
			clearTimeout(timeout);
		}
		timeout = setTimeout(function() {
            $(".alert-message").fadeOut('slow',function(){$(".alert-message").alert('close')});
		}, 6000);


        if ($body.scrollTop()) {
            $body.animate({scrollTop: 0}, 500);
        }
        else if ($html.scrollTop()) {
            $html.animate({scrollTop:0}, 500);
        }
    }

    $('.ajax-form').submit(function(){return false});
    cancel_btn.click(function(){$('#cancel-modal').modal('show')});
    cancel_modal_no.click(function(){$('#cancel-modal').modal('hide')});
    cancel_modal_yes.click(function(){ history.go(-1) });

    $('.ajax-form button[type=submit]').click(
        function(){
            $btn = $(this);
            $form =  $(this).parents('form:first');

            $(this).button('loading');

            if(typeof(CKEDITOR) != 'undefined' &&
                CKEDITOR != null){
                var ck_content = CKEDITOR.instances.content;
                var content = $('#content');
                content.val(CKEDITOR.instances.content.getData());
            }

            $.ajax({
                'url': $form.attr('action'),
                'type': $form.attr('method'),
                'data': $form.serialize(),
                'error' : function(){$(this).button('reset');show('fatal error');},
                'success' : function(text){$btn.button('reset');show(text);},
            });
        }
    )
})


