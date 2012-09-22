/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	config.language = 'en';
    config.skin  ='kama';
    config.extraPlugins = 'syntaxhighlight';

    config.toolbar_Full = [
        ['Source','-','Preview','-','Templates'],
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo'],
        ['TextColor','BGColor'],
        ['SpellChecker','Maximize'],
        '/',
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
        ['NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl'],
        ['Link','Unlink','Anchor'],
        '/',
        ['Styles','Format','Font','FontSize'],
        ['Image','Flash','Table','Code','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ], 
        ];

};
