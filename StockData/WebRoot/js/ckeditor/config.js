/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	//当提交包含有此编辑器的表单时，是否自动更新元素内的数据
	config.autoUpdateElement = true;

	//编辑器的z-index值
	//config.baseFloatZIndex = 10000;

	//设置是使用绝对目录还是相对目录，为空为相对目录
	config.baseHref = '';
	//所需要添加的CSS文件 在此添加 可使用相对路径和网站的绝对路径
	//config.contentsCss = './contents.css';
	
	config.height = 200;
	config.width = 900;
	
	//主题
	config.theme = 'default';

	//使用的工具栏 plugins/toolbar/plugin.js
	/*config.toolbar = 'Self';	//自定义，不配置此项默认显示全功能
	config.toolbar_Self =
	[
	    ['Source','-','Save','NewPage','Preview','-','Templates'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	    ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
	    '/',
	    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	    ['Link','Unlink','Anchor'],
	    ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	    '/',
	    ['Styles','Format','Font','FontSize'],
	['TextColor','BGColor']
	];*/

	//工具栏是否可以被收缩 plugins/toolbar/plugin.js
	//config.toolbarCanCollapse = false;

	//工具栏的位置 plugins/toolbar/plugin.js
	config.toolbarLocation = 'top';//可选：bottom

	//工具栏默认是否展开 plugins/toolbar/plugin.js
	config.toolbarStartupExpanded = true;

	//撤销的记录步数 plugins/undo/plugin.js
	config.undoStackSize =20;

	
};
