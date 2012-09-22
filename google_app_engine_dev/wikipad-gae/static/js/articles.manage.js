$(function() {
  // datatable init
  var oTable =$('#articles-table').dataTable(
                {
                  "aaSorting": [[ 1, "asc" ]],
                  "aoColumns": [
                                { "bSortable": false },
                                null,
                                null,
                                null,
                                null,
                               ]
                });

  $("#articles-table thead tr th:first input:checkbox").click(function() {
      var checkedStatus = this.checked;
      $("#articles-table tbody tr td:first-child input:checkbox").each(function() {
          this.checked = checkedStatus;
      });
    }); // <!--  thead select all click -->

  $("#articles-table tfoot tr th:first input:checkbox").click(function() {
      var checkedStatus = this.checked;
      $("#articles-table tbody tr td:first-child input:checkbox").each(function() {
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

  var $del_posts_form = $('#del-posts-form');
  var $del_posts_btn = $('#del-posts-btn');

  $del_posts_btn.click(
      function(){
          var anSelected = fnGetSelected( oTable );
          if(anSelected.length > 0){
            $.ajax({
               'type': $del_posts_form.attr('method'),
               'url': $del_posts_form.attr('action'),
               'data': $del_posts_form.serialize(),
               'error': function(){$del_posts_btn.button('reset');},
               'success' : function(){
                  for ( var i=0 ; i<anSelected.length ; i++ ){
                    oTable.fnDeleteRow( anSelected[i] );
                  }
                  msgbbox('delete done');
               }, // <!-- success function -->
             }); // <!-- ajax function -->
          }
          else{
            msgbbox('Please choose post to delete');
          }
        }); // <!-- del post submit function -->

    $('.get-json').click(function(){
      oTable.fnClearTable();

      $.getJSON($(this).attr('href'), function(json){
          articles = json['articles'];
          previous_cursor = json['previous_cursor'];
          next_cursor = json['next_cursor'];

          previous_span = $('#previous-span');
          more_span = $('#more-span');

          previous_link = $('#previous-span a:first');
          more_link = $('#more-span a:first');

          root =  more_link.attr('href').split('?')[0] + '?';

          if(previous_cursor != null && previous_cursor.length > 0){
              previous_span.show();
              previous_link.attr('href',(root + 'to=' + previous_cursor));
          }
          else{
              previous_span.hide();
          }

          if(next_cursor !=null && next_cursor.length > 0) {
              more_span.show();
              more_link.attr('href', (root + 'from=' + next_cursor));
          }
          else{
              more_span.hide();
          }
          $.each(articles,function(i, article) {
              oTable.fnAddData(
                      [
                      "<input type=\"checkbox\" name=\"article-ids\" value=" + article.id + ">",
                      "<a href=\"" + article.url + "\">" + article.title + "</a>",
                      article.time,
                      article.category,
                      article.tags,
                      ]
                  )
          })
      });
      return false;
    });

  // disable del post form subit
  $del_posts_form.submit(
    function(){
      return false;
    });
}); // <!-- document ready -->

