/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	__webpack_require__(1);
	
	$(document).ready(function () {
	    $('.css-pMessage h4 img').click(function () {
	        if ($(this).parent().parent().height() <= 21) {
	            $(this).parent().parent().height('auto');
	            $(this).attr('src', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAn0lEQVQoz9XRsQ3CMBCF4d8oUjpYAeQBKINYAWrIBukyQObIAGmhpkofCToWeIIVYAI6izgxpkO8zqfvLN8Z/jJK1Sn1q5MBMzSsaWQikIocyKn6Za9POw7usLfHAFTGude3spcRqDm3wUMW9u5BTXmMLmFmn2/DKKENbKtV4qCgJgvAjFruxpLiww8UlGBAG07EsjVaco0ywKj7hv00L0JaIixvQD/QAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwW7cVswAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0wNlQxNzozNzoxNSswODowMCrqrQ8AAAAASUVORK5CYII=');
	        } else {
	            $(this).parent().parent().height('20px');
	            $(this).attr('src', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAQAAAAngNWGAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAHdElNRQfhAQYRJQ9sbxDaAAAAoUlEQVQoz83QsQ2CUBSF4f8REzqdgdwBLKVwBHrGYAC2oGMCJmADKeh0gJOwgtQmz4JIiCE8KuOp7r35mnvg/+PU7YVn7ntgZA+yHS6LwFqKACusjQCoqDdYTQVumnXgxmWV9VztNUPQkecqPNkIEH12G0lWWDKxBQQbSL9YasNcz/JuPfliza3feFGlvLy8ykBlcmrk1cgRimJ1ioPsd3kDjdEqtTmpFoIAAAAldEVYdGRhdGU6Y3JlYXRlADIwMTctMDEtMDZUMTc6Mzc6MTUrMDg6MDBbtxWzAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDE3LTAxLTA2VDE3OjM3OjE1KzA4OjAwKuqtDwAAAABJRU5ErkJggg==');
	        }
	    });
	    $('#btnSubmit').click(function () {
	        $('.css-pMessage').height('auto');
	    });
	});

/***/ },
/* 1 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map