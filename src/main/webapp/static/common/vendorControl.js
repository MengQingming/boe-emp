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

	"use strict";
	
	__webpack_require__(1);
	
	__webpack_require__(5);

/***/ },
/* 1 */
/***/ function(module, exports) {

	// removed by extract-text-webpack-plugin

/***/ },
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	var _commonUtils = __webpack_require__(6);
	
	var _commonUtils2 = _interopRequireDefault(_commonUtils);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	(function () {
	    $(document).ready(function () {
	        var integerVendorID;
	        var bankObject;
	        var siteObject;
	        var active;
	
	        $('body').on('click', '#vendorGroupButton', function (e) {
	            e.preventDefault();
	            var groupId = $('#groupId').val();
	            $(this).parent().append('<div class=\'css-vendorGroup\'>\n                        <h2>\u4F9B\u5E94\u5546\u9009\u62E9</h2>\n\n                        <label for=\'\'>\u4F9B\u5E94\u5546\u540D\u79F0\uFF1A</label><div class=\'input-append\' id=\'name\'>\n                            <input class=\'span2\' id=\'appendedInputButton\' type=\'text\'>\n                            <button class=\'btn\' type=\'button\' id=\'nameSearch\'>\u641C\u7D22</button>\n                        </div>\n\n                        <label for=\'\'>\u4F9B\u5E94\u5546\u5730\u70B9\uFF1A</label><select id=\'place\' class=\'span3\'></select>\n\n                        <label for=\'\'>\u94F6\u884C\u8D26\u6237\uFF1A</label><select id=\'bank\' class=\'span3\'></select>\n\n                        <button class=\'btn btn-primary btn-small\' id=\'success\'>\u786E\u5B9A</button>\n                        <button class=\'btn btn-small\' id=\'back\'>\u5173\u95ED</button>\n                    </div>');
	        });
	
	        $('body').on('click', '#vendorGroupButtonNoBank', function (e) {
	            e.preventDefault();
	            var groupId = $('#groupId').val();
	            $(this).parent().append('<div class=\'css-vendorGroup\'>\n                            <h2>\u4F9B\u5E94\u5546\u9009\u62E9</h2>\n\n                            <label for=\'\'>\u4F9B\u5E94\u5546\u540D\u79F0\uFF1A</label><div class=\'input-append\' id=\'name\'>\n                                <input class=\'span2\' id=\'appendedInputButton\' type=\'text\'>\n                                <button class=\'btn\' type=\'button\' id=\'nameSearch\'>\u641C\u7D22</button>\n                            </div>\n\n                            <label for=\'\'>\u4F9B\u5E94\u5546\u5730\u70B9\uFF1A</label><select id=\'place\' class=\'span3\'></select>\n\n                            <button class=\'btn btn-primary btn-small\' id=\'success\'>\u786E\u5B9A</button>\n                            <button class=\'btn btn-small\' id=\'back\'>\u5173\u95ED</button>\n                        </div>');
	        });
	        //单击查询
	
	        $('body').on('click', '#nameSearch', function () {
	            var vendorName = $(this).parent().children()[0].value;
	            var dataName = {
	                "vendorName": vendorName
	            };
	            var callBack = function callBack(data) {
	                var dropdownLiArr = [];
	                for (var i = 0; i < data.row.length; i++) {
	                    dropdownLiArr[i] = '\n                        <li>\n                            <a href=\'###\'>' + data.row[i].vendorNumber + ' ' + data.row[i].vendorName + '</a>\n                            <span class=\'dropdownNone\'>' + data.row[i].id + '</span>\n                        </li>';
	                }
	                var dropdownLi = dropdownLiArr.join('');
	                $('#nameSearch').replaceWith('<div class=\'btn-group\'>\n                            <button class=\'btn dropdown-toggle\' data-toggle=\'dropdown\'>\n                                \u4F9B\u5E94\u5546\n                                <span class=\'caret\'></span>\n                            </button>\n                            <ul class=\'dropdown-menu\'>\n                                ' + dropdownLi + '\n                            </ul>\n                        </div>');
	            };
	            (0, _commonUtils2.default)('/xxwfssc/a/fssc/vendor/findVendorByName', callBack, dataName);
	        });
	        //供应商名称查询
	
	        $('body').on('click', '#name .dropdown-menu li a', function () {
	            var dropdownActive = $(this).text();
	            active = dropdownActive.split(" ");
	            $(this).parent().parent().parent().parent().children()[0].value = active[1];
	
	            integerVendorID = $(this).next().text();
	            var integerGroupOrgId = $('#groupId').val();
	            var dataName = {
	                "vendorId": integerVendorID,
	                "groupOrgId": integerGroupOrgId
	            };
	            var callBack = function callBack(data) {
	                if (data.status == false) {
	                    //alert("供应商与合同机构不匹配，请选择正确供应商或合同机构！");
	                    top.$.jBox.alert('供应商与合同机构不匹配，请选择正确供应商或合同机构！');
	                } else {
	                    siteObject = data;
	                    var dropdownLiArr = [];
	                    for (var i = 0; i < data.row.length; i++) {
	                        dropdownLiArr[i] = '<option>' + data.row[i].vendorSiteCode + '</option>';
	                    }
	                    var dropdownLi = dropdownLiArr.join('');
	                    $('#place').append(dropdownLi);{
	                        var bankDataName = {
	                            "vendorSiteId": data.row[0].id
	                        };
	                        var bankCallBack = function bankCallBack(data) {
	                            bankObject = data;
	                            var bankDropdownLiArr = [];
	                            for (var _i = 0; _i < data.row.length; _i++) {
	                                var bankAccountName = void 0;
	                                if (data.row[_i].bankAccountName) {
	                                    bankAccountName = data.row[_i].bankAccountName;
	                                } else {
	                                    bankAccountName = "";
	                                }
	                                bankDropdownLiArr[_i] = "<option>" + data.row[_i].bankAccountNum + "</option>";
	                            }
	                            var bankDropdownLi = bankDropdownLiArr.join('');
	                            $('#bank').append(bankDropdownLi);
	                        };
	                        (0, _commonUtils2.default)('/xxwfssc/a/fssc/vendor/findVendorrAccountBySiteId', bankCallBack, bankDataName);
	                    }
	                    //自动返回银行账号
	                }
	            };
	            (0, _commonUtils2.default)('/xxwfssc/a/fssc/vendor/findVendorById', callBack, dataName);
	        });
	        //选择供应商名称
	
	        $('body').on('focus', '.css-vendorGroup #name input', function () {
	            $(this).next().replaceWith("<button class='btn' type='button' id='nameSearch'>搜索</button>");
	            $('#place').children().remove();
	            $('#bank').children().remove();
	        });
	        //name二次选择
	
	        $('body').on('click', '.css-vendorGroup #success', function (event) {
	            event.stopPropagation();
	            event.preventDefault();
	            // integerVendorID;
	            $('#vendorId').val(integerVendorID);
	            //供应商ID
	            $('#vendorNo').val(active[0]);
	            // 供应商编号
	            $('#vendorName').val(active[1]);
	            // 供应商名称
	            var bankAccountNum = $('#bank').val();
	            $('#vendorBankAccNo').val(bankAccountNum);
	            //卡号
	            var vendorSiteId = siteObject.row[0].id;
	            $('#vendorSiteId').val(vendorSiteId);
	            //地点ID
	            var vendorSiteCode = siteObject.row[0].vendorSiteCode;
	            $('#vendorSiteCode').val(vendorSiteCode);
	            //地点名
	            for (var i = 0; i < bankObject.row.length; i++) {
	                if (bankObject.row[i].bankAccountNum == bankAccountNum) {
	                    if (bankObject.row[i].bankAccountName) {
	                        $('#vendorBankAccName').val(bankObject.row[i].bankAccountName);
	                        //供应商银行账户名称
	                    } else {
	                        $('#vendorBankAccName').val("");
	                    }
	                    if (bankObject.row[i].bankName) {
	                        $('#vendorBankName').val(bankObject.row[i].bankName);
	                        //供应商开户银行
	                    } else {
	                        $('#vendorBankName').val("");
	                    }
	                }
	            }
	            $(this).parent().html("").remove();
	        });
	
	        $('body').on('click', '.css-vendorGroup #back', function (event) {
	            event.stopPropagation();
	            event.preventDefault();
	            $(this).parent().html("").remove();
	        });
	    });
	})();

/***/ },
/* 6 */
/***/ function(module, exports) {

	"use strict";
	
	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	var searchAjax = function searchAjax(url, callBack, data) {
	    $.ajax({
	        type: "post",
	        data: data,
	        url: url,
	        dataType: "json",
	        success: callBack
	    });
	};
	//AJAX
	
	exports.default = searchAjax;

/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map