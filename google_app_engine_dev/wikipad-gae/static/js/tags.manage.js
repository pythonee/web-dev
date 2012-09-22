$(function() {
  // datatable init
  var oTable =$('#tag-table').dataTable(
                {
                  "aaSorting": [[ 1, "asc" ]],
                  "aoColumns": [
                                { "bSortable": false },
                                null,
                                null,
                               ],
                });

  var $original_tag = $('#original-tag');

  $("#tag-table thead tr th:first input:checkbox").click(function() {
      var checkedStatus = this.checked;
      $("#tag-table tbody tr td:first-child input:checkbox").each(function() {
          this.checked = checkedStatus;
      });
    }); // <!--  thead select all click -->

  $("#tag-table tfoot tr th:first input:checkbox").click(function() {
      var checkedStatus = this.checked;
      $("#tag-table tbody tr td:first-child input:checkbox").each(function() {
          this.checked = checkedStatus;
      });
    }); // <!-- tfoot select all click -->

  function fnGetSelected( oTableLocal )
  {
    var aReturn = new Array();
    var aTrs = oTableLocal.fnGetNodes();

    for ( var i=0 ; i<aTrs.length ; i++ )
    {
      var checkbox = $(aTrs[i]).children('td:first').children('input:checkbox');
      if (checkbox.attr('checked'))
      {
        aReturn.push( aTrs[i] );
      }
    }
    return aReturn;
  }

  var $add_tag_form = $('#add-tag-form');
  var $add_submit_btn = $('#add-submit-btn');

  $add_submit_btn.click(
        function() {
          var new_tag_name = $('#new_tag_name').val();
          if(new_tag_name.length > 0){
              $add_submit_btn.button('loading');
              $.ajax({
                  'type': $add_tag_form.attr('method'),
                  'url': $add_tag_form.attr('action'),
                  'data': $add_tag_form.serialize(),
                  'dataType': 'json',
                  'error': function(){$add_submit_btn.button('reset');},
                  'success' : function(resp){
                      $.each(resp, function() {
                          $.each(this, function(key, value) {
                              oTable.fnAddData(
                                  [
                                  "<input type=\"checkbox\" name=\"tag\" value=" + key + ">",
                                  value,
                                  0
                                  ]
                                  )
                          }); // <!-- loop json -->
                      }); // <!-- loop json list -->
                      $add_submit_btn.button('reset');
                      msgbbox("add tags success");
                  }, // <!-- success function -->
              }); // <!-- ajax function -->
          }
          else{
            $add_submit_btn.button('reset');
            msgbbox('Please fill the tag name field');
          }
          }); // <!-- add tag submit btn click -->

  var $rename_tag_form = $('#rename-tag-form');
  var $rename_tag_btn = $('#rename-submit-btn');

  $rename_tag_btn.click(
    function(){
        var dest_name = $('#dest_name').val();
        if(dest_name.length > 0){
            $rename_tag_btn.button('loading');
            $.ajax({

                'type': $rename_tag_form.attr('method'),
                'url': $rename_tag_form.attr('action'),
                'data': $rename_tag_form.serialize(),
                'error': function(){$rename_tag_btn.button('reset');},
                'success' : function() {
                    $rename_tag_btn.button('reset');
                    msgbbox("refresh");
                }
            });
        }
        else{
            $rename_tag_btn.button('reset');
            msgbbox('Please fill the new tag name field');
        }
    });


  var $del_tags_form = $('#del-tags-form');
  var $del_tags_btn = $('#del-tags-btn');

  $del_tags_btn.click(
      function(){
        $del_tags_btn.button('loading');

        var anSelected = fnGetSelected( oTable );
        if(anSelected.length > 0){
            $.ajax({
               'type': $del_tags_form.attr('method'),
               'url': $del_tags_form.attr('action'),
               'data': $del_tags_form.serialize(),
               'error': function(){$del_tags_btn.button('reset');},
               'success' : function(){
                  for ( var i=0 ; i<anSelected.length ; i++ ){
                    oTable.fnDeleteRow( anSelected[i] );
                  }
                  $del_tags_btn.button('reset');
                  msgbbox("delete tags success");
               }, // <!-- success function -->
             }); // <!-- ajax function -->
        }
        else{
            $del_tags_btn.button('reset');
            msgbbox('Please choose some tags to delete');
        }
        }); // <!-- del tag submit function -->

  $sync_tag_form = $('#sync-tag-form');
  $sync_submit_btn = $('#sync-submit-btn');

  $sync_submit_btn.click(
    function(){
        $sync_submit_btn.button('loading');
        $.ajax({
           'type': $sync_tag_form.attr('method'),
           'url': $sync_tag_form.attr('action'),
           'data': $sync_tag_form.serialize(),
           'error': function(){$sync_submit_btn.button('reset');},
           'success' : function(){
               $sync_submit_btn.button('reset');
               msgbbox('have add sync task to task queue');
           }, // <!-- success function -->
         }); // <!-- ajax function -->
    }); // <!-- sync submit click funtion -->

  $('form').submit(function(){
      return false;
  });

}); // <!-- document ready -->

