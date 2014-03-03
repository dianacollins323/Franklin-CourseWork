/*global jQuery */
/*! 
* simplr gallery 1.1
*
* Copyright 2013, Sean Gofus - http://www.seangofus.com
* Released under the WTFPL license - http://sam.zoy.org/wtfpl/
*
* Date: Tue July 30 9:18:00 2013 -06000
*/
(function ($) {
        $.fn.simplrgallery = function (options) {
                //***CHANGE THIS VALUE TO MATCH YOUR FLICKR API KEY***//
                var apiKey = 'def271f854de4c5cd79591f0b1cdc4e9';
                //******************************************************//
                var photoSet;
                var parent = $(this);
                var thumbSelector;
                var photoSelector;
                var photoSize;
                var caption;
                var externalCss;
                var settings = {
                        thumbSelector: 'thumbs',
                        photoSelector: 'mainImage',
                        photoSize: 'medium',
                        caption: false,
                        externalCss: false
                }
                if(options){ 
                        $.extend( settings, options );
                }
                if(settings.photoSet){
                        photoSet = settings.photoSet;
                }
                if(settings.thumbSelector){
                        thumbSelector = settings.thumbSelector;
                }
                if(settings.photoSize){
                        photoSize = settings.photoSize.toLowerCase();
                }
                if(settings.photoSelector){
                        photoSelector = settings.photoSelector;
                }
                if(photoSet)
                {
                        
                        
                        if(photoSize == 'small'){
                                photoSize = 'Small 320'
                        }else if(photoSize == 'medium'){
                                photoSize = 'Medium 640'
                        }else if(photoSize == 'large'){
                                photoSize = 'Large'
                        }else{
                                photoSize = 'Medium 640'
                        }
        
                        if(settings.externalCss == false){
                                var css = '<style>ul#'+thumbSelector+'{list-style-type:none;padding:10px;}\
                                                        \nul#'+thumbSelector+' li.thumbnail{cursor:pointer;float:left;margin:0 5px 5px 0;}\
                                                        \n#shadowbox{background:rgba(0,0,0,0.8);cursor:pointer;position:fixed;height:100%;left:0;top:0;width:100%;z-index:500}\
                                                        \n#'+photoSelector+'{margin:10px 0;position:fixed;top:0;left:0;z-index:9999;}\
                                                        \n#'+photoSelector+' img{cursor:pointer;display:block;border:7px solid #FFF;box-shadow:3px 3px 10px #000}\
                                                        \n#photoCaption{background:#FFF;display:block;margin-top: -20px;padding:10px 5px;position:absolute;text-align:center;z-index: 600;box-shadow: 3px 3px 10px #000;}\
                                                        </style>';
                                $('head').append(css);
                        }
                        
                        $(parent).append('<ul id="'+thumbSelector+'"/>');
                        $.ajax({
                                url: 'http://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key='+apiKey+'&photoset_id='+photoSet+'&format=json&jsoncallback=?',
                                dataType: "jsonp",
                                success: function(data) {
                                        $(data.photoset.photo).each(function(i, thumb){
                                                var photoId = thumb.id;
                                                var secret = thumb.secret;
                                                var server = thumb.server;
                                                var farm = thumb.farm;
                                                var title = thumb.title;
                                                var caption = thumb.title;
                                                $('#'+thumbSelector).append('<li id="'+photoId+'" class="thumbnail"><img src="http://farm'+farm+'.staticflickr.com/'+server+'/'+photoId+'_'+secret+'_q.jpg" width="150" height="150" alt="'+title+'" title="'+title+'"/></li>');
                                                });
                                                
                                        }
                        });
                        
                        $(parent).on('click', '.thumbnail img', function(event){
                                var photoId = $(this).parent().attr('id');
                                var photoCaption = $(this).attr('title');
                                var windowW = $(window).width();
                                var windowH = $(window).height();
                                $('body').append('<div id="shadowbox"/>');
                                $(parent).append('<div id="'+photoSelector+'"/>');
                                $.ajax({
                                        url: 'http://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key='+apiKey+'&photo_id='+photoId+'&format=json&jsoncallback=?',
                                        dataType: "jsonp",
                                        success: function(data) {
                                                $(data.sizes.size).each(function(i, photo){
                                                        if($(this).attr('label') == photoSize){
                                                                var source = $(this).attr('source');
                                                                var width = $(this).attr('width');
                                                                var height = $(this).attr('height');
                                                                $('#'+photoSelector).append('<img src="'+source+'" height="'+height+'" width="'+width+'" />');
                                                                $('#'+photoSelector+' img').css({
                                                                        'top': (windowH-height)/2,
                                                                        'left': (windowW-width)/2,
                                                                        'position': 'absolute'
                                                                });
                                                                if(settings.caption == true){
                                                                        $('#'+photoSelector).append('<span id="photoCaption">'+photoCaption+'</span>');
                                                                        var photoOffset = $('#'+photoSelector+' img').offset();
                                                                        $('#photoCaption').css({
                                                                                'top': $('#'+photoSelector+' img').height()+photoOffset.top,
                                                                                'left': ((windowW-width)/2)+25,
                                                                                'width': $('#'+photoSelector+' img').width()-50,
                                                                                'position': 'absolute',
                                                                                'z-index': '9999'
                                                                        });
                                                                }
                                                                $('#'+photoSelector).fadeIn();
                                                                
                                                        }
                                                        
                                                });
                                        }
                                });
                        });
                        $(parent).on('click', '#'+photoSelector,function(){
                                $(this).fadeOut('slow', function(){
                                        $(this).remove();
                                        $('#shadowbox').remove();
                                });
                        });
                        $('body').on('click', '#shadowbox', function(){
                                $('#'+photoSelector).remove();
                                $(this).fadeOut('slow', function(){
                                        $(this).remove();
                                });
                        });
                }else{
                        $(parent).append('<p id="simplrError">Simplr Gallery requires a Photoset ID to passed to the function.<br/><br/><small>$("#gallery").simplrgallery({photoSet: "<em>PHOTOSET ID GOES HERE</em>"});</small></p>');
                        $('#simplrError').css({
                                'background-color': '#8a95a4',
                                'border': '1px solid #596069',
                                'border-radius': '3px',
                                'color': 'white',
                                'font-size': '18px',
                                'margin': '10px auto',
                                'padding': '10px',
                                'text-align': 'center',
                                'text-shadow': '0 1px 0 #596069',
                                'width': '70%'
                        });
                        $('#simplrError small').css({
                                'font-size': '14px',
                        });
                        $('#simplrError small em').css({
                                'color': '#f2b8d6',
                                'font-size': '16px',
                                'font-style':'none'
                        });
                }
        };
})(jQuery);