package com.seleniumhacks.core;

import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.core.utils.selenium.DriverPool;

/**
 * This class utilizes the javascript raw code to implement drag drop feature in
 * Selenium (of course when normal drag drop functions does not work)
 * 
 * @author jaikantdangi
 *
 */
public class DragDropUtils {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		// get your driver here
		WebDriver driver = DriverPool.getWebDriver("chrome");
		driver.navigate().to("http://marceljuenemann.github.io/angular-drag-and-drop-lists/demo/#/nested");
		driver.manage().window().maximize();

		// USING CSS SELECTOR
		// just copy pasted absolute selectors here, you can use relative path , Just
		// wanted to do it quick :-)
		String dragElement = "body > div.container > div.ng-scope > div.nestedDemo.row.ng-scope > div.col-md-10.ng-scope > div.row > div:nth-child(1) > div > div > ul > li:nth-child(1) > div > div:nth-child(2) > ul > li:nth-child(1) > div";
		String dropElement = "body > div.container > div.ng-scope > div.nestedDemo.row.ng-scope > div.col-md-10.ng-scope > div.row > div:nth-child(1) > div > div > ul > li:nth-child(1) > div > div:nth-child(3) > ul > li > div";
		dragDropJavaScriptUsingCssSelector(driver, dragElement, dropElement);
		
		// USING XPATH
		String dragElementXpath = "//div[.='Item 1']";
		String dropElementXpath = "//div[.='Item 3']";
		dragDropJavaScriptUsingXpath(driver, dragElementXpath, dropElementXpath);
	}

	/**
	 * Method to perform drag drop using javascript using css selector
	 * @param driver WebDrivr
	 * @param dragElement [css selector]
	 * @param dropElement [css selector]
	 * @throws InterruptedException
	 */
	public static void dragDropJavaScriptUsingCssSelector(WebDriver driver, String dragElement, String dropElement)
			throws InterruptedException {
		String jsDragDropSnippet = "!function t(e,r,n){function a(i,u){if(!r[i]){if(!e[i]){var s=\"function\"==typeof require&&require;if(!u&&s)return s(i,!0);if(o)return o(i,!0);var c=new Error(\"Cannot find module '\"+i+\"'\");throw c.code=\"MODULE_NOT_FOUND\",c}var f=r[i]={exports:{}};e[i][0].call(f.exports,(function(t){var r=e[i][1][t];return a(r||t)}),f,f.exports,t,e,r,n)}return r[i].exports}for(var o=\"function\"==typeof require&&require,i=0;i<n.length;i++)a(n[i]);return a}({1:[function(t,e,r){var n=t(\"./src/index.js\");\"function\"==typeof define&&define(\"dragMock\",(function(){return n})),window.dragMock=n},{\"./src/index.js\":5}],2:[function(t,e,r){var n=function(){this.dataByFormat={},this.dropEffect=\"none\",this.effectAllowed=\"all\",this.files=[],this.types=[]};n.prototype.clearData=function(t){t?(delete this.dataByFormat[t],function(t,e){var r=t.indexOf(e);r>=0&&t.splice(r,1)}(this.types,t)):(this.dataByFormat={},this.types=[])},n.prototype.getData=function(t){return this.dataByFormat[t]},n.prototype.setData=function(t,e){return this.dataByFormat[t]=e,this.types.indexOf(t)<0&&this.types.push(t),!0},n.prototype.setDragImage=function(){},e.exports=n},{}],3:[function(t,e,r){function n(){}function a(t,e,r){if(\"function\"==typeof e&&(r=e,e=null),!t||\"object\"!=typeof t)throw new Error(\"Expected first parameter to be a targetElement. Instead got: \"+t);return{targetElement:t,eventProperties:e||{},configCallback:r||n}}function o(t,e,r,n,a,o){e.forEach((function(e){var u=i.createEvent(e,a,n);(function(t,e,r){e&&(e.length<2?r&&e(t):e(t,t.type))})(u,o,e===r),t.dispatchEvent(u)}))}var i=t(\"./eventFactory\"),u=t(\"./DataTransfer\"),s=function(){this.lastDragSource=null,this.lastDataTransfer=null,this.pendingActionsQueue=[]};s.prototype._queue=function(t){this.pendingActionsQueue.push(t),1===this.pendingActionsQueue.length&&this._queueExecuteNext()},s.prototype._queueExecuteNext=function(){if(0!==this.pendingActionsQueue.length){var t=this,e=this.pendingActionsQueue[0],r=function(){t.pendingActionsQueue.shift(),t._queueExecuteNext()};0===e.length?(e.call(this),r()):e.call(this,r)}},s.prototype.dragStart=function(t,e,r){var n=a(t,e,r),i=[\"mousedown\",\"dragstart\",\"drag\"],s=new u;return this._queue((function(){o(n.targetElement,i,\"drag\",s,n.eventProperties,n.configCallback),this.lastDragSource=t,this.lastDataTransfer=s})),this},s.prototype.dragEnter=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseover\",\"dragenter\"];return this._queue((function(){o(n.targetElement,i,\"dragenter\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.dragOver=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseover\",\"dragover\"];return this._queue((function(){o(n.targetElement,i,\"drag\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.dragLeave=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseover\",\"dragleave\"];return this._queue((function(){o(n.targetElement,i,\"dragleave\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.drop=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseup\",\"drop\"],u=[\"dragend\"];return this._queue((function(){o(n.targetElement,i,\"drop\",this.lastDataTransfer,n.eventProperties,n.configCallback),this.lastDragSource&&o(this.lastDragSource,u,\"drop\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.then=function(t){return this._queue((function(){t.call(this)})),this},s.prototype.delay=function(t){return this._queue((function(e){window.setTimeout(e,t)})),this},e.exports=s},{\"./DataTransfer\":2,\"./eventFactory\":4}],4:[function(t,e,r){function n(t,e){for(var r in e)e.hasOwnProperty(r)&&(t[r]=e[r]);return t}function a(t,e,r){var a;if(\"MouseEvent\"===e)(a=document.createEvent(\"MouseEvent\")).initEvent(t,!0,!0);else(a=document.createEvent(\"CustomEvent\")).initCustomEvent(t,!0,!0,0);return r&&n(a,r),a}function o(t,e,r){try{return function(t,e,r){\"DragEvent\"===e&&(e=\"CustomEvent\");var a=window[e],o={view:window,bubbles:!0,cancelable:!0};n(o,r);var i=new a(t,o);return n(i,r),i}(t,e,r)}catch(n){return a(t,e,r)}}var i=t(\"./DataTransfer\"),u=[\"drag\",\"dragstart\",\"dragenter\",\"dragover\",\"dragend\",\"drop\",\"dragleave\"],s={createEvent:function(t,e,r){var n=\"CustomEvent\";t.match(/^mouse/)&&(n=\"MouseEvent\");var a=o(t,n,e);return u.indexOf(t)>-1&&(a.dataTransfer=r||new i),a}};e.exports=s},{\"./DataTransfer\":2}],5:[function(t,e,r){function n(t,e,r){return t[e].apply(t,r)}var a=t(\"./DragDropAction\"),o={dragStart:function(t,e,r){return n(new a,\"dragStart\",arguments)},dragEnter:function(t,e,r){return n(new a,\"dragEnter\",arguments)},dragOver:function(t,e,r){return n(new a,\"dragOver\",arguments)},dragLeave:function(t,e,r){return n(new a,\"dragLeave\",arguments)},drop:function(t,e,r){return n(new a,\"drop\",arguments)},delay:function(t,e,r){return n(new a,\"delay\",arguments)},DataTransfer:t(\"./DataTransfer\"),DragDropAction:t(\"./DragDropAction\"),eventFactory:t(\"./eventFactory\")};e.exports=o},{\"./DataTransfer\":2,\"./DragDropAction\":3,\"./eventFactory\":4}]},{},[1]);";
		((JavascriptExecutor) driver).executeScript("eval(arguments[0]);", jsDragDropSnippet);
		boolean dragMockExists = (boolean) ((JavascriptExecutor) driver).executeScript("return !!window.dragMock;");
		if (dragMockExists == false) {
			throw new InterruptedException("Unable to add the drag mock to the driver");
		}
		((JavascriptExecutor) driver).executeScript("var startEle = document.querySelector('" + dragElement
				+ "'); var endEle = document.querySelector('" + dropElement
				+ "');var wait = 150; window.dragMock.dragStart(startEle).delay(wait).dragEnter(endEle).delay(wait).dragOver(endEle).delay(wait).drop(endEle).then(arguments[arguments.length - 1]);");
	}
	
	/**
	 * Method to perform drag drop using javascript using xpath selctor
	 * @param driver WebDrivr
	 * @param dragElement [xpath selector]
	 * @param dropElement [xpath selector]
	 * @throws InterruptedException
	 */
	public static void dragDropJavaScriptUsingXpath(WebDriver driver, String dragElement, String dropElement)
			throws InterruptedException {
		String jsDragDropSnippet = "!function t(e,r,n){function a(i,u){if(!r[i]){if(!e[i]){var s=\"function\"==typeof require&&require;if(!u&&s)return s(i,!0);if(o)return o(i,!0);var c=new Error(\"Cannot find module '\"+i+\"'\");throw c.code=\"MODULE_NOT_FOUND\",c}var f=r[i]={exports:{}};e[i][0].call(f.exports,(function(t){var r=e[i][1][t];return a(r||t)}),f,f.exports,t,e,r,n)}return r[i].exports}for(var o=\"function\"==typeof require&&require,i=0;i<n.length;i++)a(n[i]);return a}({1:[function(t,e,r){var n=t(\"./src/index.js\");\"function\"==typeof define&&define(\"dragMock\",(function(){return n})),window.dragMock=n},{\"./src/index.js\":5}],2:[function(t,e,r){var n=function(){this.dataByFormat={},this.dropEffect=\"none\",this.effectAllowed=\"all\",this.files=[],this.types=[]};n.prototype.clearData=function(t){t?(delete this.dataByFormat[t],function(t,e){var r=t.indexOf(e);r>=0&&t.splice(r,1)}(this.types,t)):(this.dataByFormat={},this.types=[])},n.prototype.getData=function(t){return this.dataByFormat[t]},n.prototype.setData=function(t,e){return this.dataByFormat[t]=e,this.types.indexOf(t)<0&&this.types.push(t),!0},n.prototype.setDragImage=function(){},e.exports=n},{}],3:[function(t,e,r){function n(){}function a(t,e,r){if(\"function\"==typeof e&&(r=e,e=null),!t||\"object\"!=typeof t)throw new Error(\"Expected first parameter to be a targetElement. Instead got: \"+t);return{targetElement:t,eventProperties:e||{},configCallback:r||n}}function o(t,e,r,n,a,o){e.forEach((function(e){var u=i.createEvent(e,a,n);(function(t,e,r){e&&(e.length<2?r&&e(t):e(t,t.type))})(u,o,e===r),t.dispatchEvent(u)}))}var i=t(\"./eventFactory\"),u=t(\"./DataTransfer\"),s=function(){this.lastDragSource=null,this.lastDataTransfer=null,this.pendingActionsQueue=[]};s.prototype._queue=function(t){this.pendingActionsQueue.push(t),1===this.pendingActionsQueue.length&&this._queueExecuteNext()},s.prototype._queueExecuteNext=function(){if(0!==this.pendingActionsQueue.length){var t=this,e=this.pendingActionsQueue[0],r=function(){t.pendingActionsQueue.shift(),t._queueExecuteNext()};0===e.length?(e.call(this),r()):e.call(this,r)}},s.prototype.dragStart=function(t,e,r){var n=a(t,e,r),i=[\"mousedown\",\"dragstart\",\"drag\"],s=new u;return this._queue((function(){o(n.targetElement,i,\"drag\",s,n.eventProperties,n.configCallback),this.lastDragSource=t,this.lastDataTransfer=s})),this},s.prototype.dragEnter=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseover\",\"dragenter\"];return this._queue((function(){o(n.targetElement,i,\"dragenter\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.dragOver=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseover\",\"dragover\"];return this._queue((function(){o(n.targetElement,i,\"drag\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.dragLeave=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseover\",\"dragleave\"];return this._queue((function(){o(n.targetElement,i,\"dragleave\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.drop=function(t,e,r){var n=a(t,e,r),i=[\"mousemove\",\"mouseup\",\"drop\"],u=[\"dragend\"];return this._queue((function(){o(n.targetElement,i,\"drop\",this.lastDataTransfer,n.eventProperties,n.configCallback),this.lastDragSource&&o(this.lastDragSource,u,\"drop\",this.lastDataTransfer,n.eventProperties,n.configCallback)})),this},s.prototype.then=function(t){return this._queue((function(){t.call(this)})),this},s.prototype.delay=function(t){return this._queue((function(e){window.setTimeout(e,t)})),this},e.exports=s},{\"./DataTransfer\":2,\"./eventFactory\":4}],4:[function(t,e,r){function n(t,e){for(var r in e)e.hasOwnProperty(r)&&(t[r]=e[r]);return t}function a(t,e,r){var a;if(\"MouseEvent\"===e)(a=document.createEvent(\"MouseEvent\")).initEvent(t,!0,!0);else(a=document.createEvent(\"CustomEvent\")).initCustomEvent(t,!0,!0,0);return r&&n(a,r),a}function o(t,e,r){try{return function(t,e,r){\"DragEvent\"===e&&(e=\"CustomEvent\");var a=window[e],o={view:window,bubbles:!0,cancelable:!0};n(o,r);var i=new a(t,o);return n(i,r),i}(t,e,r)}catch(n){return a(t,e,r)}}var i=t(\"./DataTransfer\"),u=[\"drag\",\"dragstart\",\"dragenter\",\"dragover\",\"dragend\",\"drop\",\"dragleave\"],s={createEvent:function(t,e,r){var n=\"CustomEvent\";t.match(/^mouse/)&&(n=\"MouseEvent\");var a=o(t,n,e);return u.indexOf(t)>-1&&(a.dataTransfer=r||new i),a}};e.exports=s},{\"./DataTransfer\":2}],5:[function(t,e,r){function n(t,e,r){return t[e].apply(t,r)}var a=t(\"./DragDropAction\"),o={dragStart:function(t,e,r){return n(new a,\"dragStart\",arguments)},dragEnter:function(t,e,r){return n(new a,\"dragEnter\",arguments)},dragOver:function(t,e,r){return n(new a,\"dragOver\",arguments)},dragLeave:function(t,e,r){return n(new a,\"dragLeave\",arguments)},drop:function(t,e,r){return n(new a,\"drop\",arguments)},delay:function(t,e,r){return n(new a,\"delay\",arguments)},DataTransfer:t(\"./DataTransfer\"),DragDropAction:t(\"./DragDropAction\"),eventFactory:t(\"./eventFactory\")};e.exports=o},{\"./DataTransfer\":2,\"./DragDropAction\":3,\"./eventFactory\":4}]},{},[1]);";
		((JavascriptExecutor) driver).executeScript("eval(arguments[0]);", jsDragDropSnippet);
		boolean dragMockExists = (boolean) ((JavascriptExecutor) driver).executeScript("return !!window.dragMock;");
		if (dragMockExists == false) {
			throw new InterruptedException("Unable to add the drag mock to the driver");
		}
		dragElement = '"' + dragElement + '"';
		dropElement = '"' + dropElement + '"';
		
		String scriptToExecute = "var startEle = document.evaluate(" + dragElement + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; var endEle = document.evaluate(" + dropElement + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;var wait = 150; window.dragMock.dragStart(startEle).delay(wait).dragEnter(endEle).delay(wait).dragOver(endEle).delay(wait).drop(endEle).then(arguments[arguments.length - 1]);";
		System.out.println(scriptToExecute);
		((JavascriptExecutor) driver).executeScript(scriptToExecute);
	}

}
