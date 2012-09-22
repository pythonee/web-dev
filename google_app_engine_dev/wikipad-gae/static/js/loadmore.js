$(function() {
	var $window = $(window);
	var $content = $('.loadmore');
	var top = $content.offset().top;
	var $loading = $('<div/>');
	var loading = false;
	var $next_url = $('#cursor>a:first');
	var next_url = '';

	function set_next_url() {
		if ($next_url.length) {
			next_url = $next_url.attr('href');
		} else {
			$window.unbind('scroll', load_more);
			next_url = '';
		}
	}

	function load() {
		$loading.load(next_url + ' .loadmore', function() {
			$next_url = $loading.find('#cursor>a:first');
			set_next_url();
			$('#cursor').detach();
			$loading.appendTo($content).slideDown(1000);
			loading = false;
		});
		_gaq.push(['_trackEvent', 'Page', 'Load', next_url]);
	}

	function load_more() {
		if (!loading && ($window.scrollTop() + $window.height() - top - $content.outerHeight() > 80)) {
			loading = true;
			load();
		}
	}

	$window.bind('scroll', load_more);
	set_next_url();
});

