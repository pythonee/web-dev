(function(){
	var $scroller;
	var $body = $('body');
	var $html = $('html');
	if ($body.scrollTop()) {
		$scroller = $body;
	} else if ($html.scrollTop()) {
		$scroller = $html;
	} else {
		$body.scrollTop(1); // 一些浏览器的body元素不支持scrollTop()，因此调用它来判断
		if ($body.scrollTop()) {
			$scroller = $body.scrollTop(0);
		} else {
			$scroller = $html;
		}
	}
	function scrollTo(top) {
		$scroller.animate({scrollTop: top < 0 ? 0 : top}, 1000);
	}
	$body.on('click', 'a[href^=#]', function(ev){ // 只捕捉URL以#开头的A元素的click事件
		var hash = this.hash;
		if (hash) {
			var $hash = $(hash);
			if ($hash.length) {
				scrollTo($hash.offset().top);
				ev.preventDefault();
			}
		} else {
			scrollTo(0);
			ev.preventDefault();
		}
	});
})();