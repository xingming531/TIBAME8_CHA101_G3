var CountUp=function(target,startVal,endVal,decimals,duration,options){var self=this;self.version=function(){return"1.9.2"};self.options={useEasing:true,useGrouping:true,separator:",",decimal:".",easingFn:easeOutExpo,formattingFn:formatNumber,prefix:"",suffix:"",numerals:[]};if(options&&typeof options==="object"){for(var key in self.options){if(options.hasOwnProperty(key)&&options[key]!==null){self.options[key]=options[key]}}}if(self.options.separator===""){self.options.useGrouping=false}else{self.options.separator=""+self.options.separator}var lastTime=0;var vendors=["webkit","moz","ms","o"];for(var x=0;x<vendors.length&&!window.requestAnimationFrame;++x){window.requestAnimationFrame=window[vendors[x]+"RequestAnimationFrame"];window.cancelAnimationFrame=window[vendors[x]+"CancelAnimationFrame"]||window[vendors[x]+"CancelRequestAnimationFrame"]}if(!window.requestAnimationFrame){window.requestAnimationFrame=function(callback,element){var currTime=new Date().getTime();var timeToCall=Math.max(0,16-(currTime-lastTime));var id=window.setTimeout(function(){callback(currTime+timeToCall)},timeToCall);lastTime=currTime+timeToCall;return id}}if(!window.cancelAnimationFrame){window.cancelAnimationFrame=function(id){clearTimeout(id)}}function formatNumber(num){num=num.toFixed(self.decimals);num+="";var x,x1,x2,x3,i,l;x=num.split(".");x1=x[0];x2=x.length>1?self.options.decimal+x[1]:"";if(self.options.useGrouping){x3="";for(i=0,l=x1.length;i<l;++i){if(i!==0&&((i%3)===0)){x3=self.options.separator+x3}x3=x1[l-i-1]+x3}x1=x3}if(self.options.numerals.length){x1=x1.replace(/[0-9]/g,function(w){return self.options.numerals[+w]});x2=x2.replace(/[0-9]/g,function(w){return self.options.numerals[+w]})}return self.options.prefix+x1+x2+self.options.suffix}function easeOutExpo(t,b,c,d){return c*(-Math.pow(2,-10*t/d)+1)*1024/1023+b}function ensureNumber(n){return(typeof n==="number"&&!isNaN(n))}self.initialize=function(){if(self.initialized){return true}self.error="";self.d=(typeof target==="string")?document.getElementById(target):target;if(!self.d){self.error="[CountUp] target is null or undefined";return false}self.startVal=Number(startVal);self.endVal=Number(endVal);if(ensureNumber(self.startVal)&&ensureNumber(self.endVal)){self.decimals=Math.max(0,decimals||0);self.dec=Math.pow(10,self.decimals);self.duration=Number(duration)*1000||2000;self.countDown=(self.startVal>self.endVal);self.frameVal=self.startVal;self.initialized=true;return true}else{self.error="[CountUp] startVal ("+startVal+") or endVal ("+endVal+") is not a number";return false}};self.printValue=function(value){var result=self.options.formattingFn(value);if(self.d.tagName==="INPUT"){this.d.value=result}else{if(self.d.tagName==="text"||self.d.tagName==="tspan"){this.d.textContent=result}else{this.d.innerHTML=result}}};self.count=function(timestamp){if(!self.startTime){self.startTime=timestamp}self.timestamp=timestamp;var progress=timestamp-self.startTime;self.remaining=self.duration-progress;if(self.options.useEasing){if(self.countDown){self.frameVal=self.startVal-self.options.easingFn(progress,0,self.startVal-self.endVal,self.duration)}else{self.frameVal=self.options.easingFn(progress,self.startVal,self.endVal-self.startVal,self.duration)}}else{if(self.countDown){self.frameVal=self.startVal-((self.startVal-self.endVal)*(progress/self.duration))}else{self.frameVal=self.startVal+(self.endVal-self.startVal)*(progress/self.duration)}}if(self.countDown){self.frameVal=(self.frameVal<self.endVal)?self.endVal:self.frameVal}else{self.frameVal=(self.frameVal>self.endVal)?self.endVal:self.frameVal}self.frameVal=Math.round(self.frameVal*self.dec)/self.dec;self.printValue(self.frameVal);if(progress<self.duration){self.rAF=requestAnimationFrame(self.count)}else{if(self.callback){self.callback()}}};self.start=function(callback){if(!self.initialize()){return}self.callback=callback;self.rAF=requestAnimationFrame(self.count)};self.pauseResume=function(){if(!self.paused){self.paused=true;cancelAnimationFrame(self.rAF)}else{self.paused=false;delete self.startTime;self.duration=self.remaining;self.startVal=self.frameVal;requestAnimationFrame(self.count)}};self.reset=function(){self.paused=false;delete self.startTime;self.initialized=false;if(self.initialize()){cancelAnimationFrame(self.rAF);self.printValue(self.startVal)}};self.update=function(newEndVal){if(!self.initialize()){return}newEndVal=Number(newEndVal);if(!ensureNumber(newEndVal)){self.error="[CountUp] update() - new endVal is not a number: "+newEndVal;return}self.error="";if(newEndVal===self.frameVal){return}cancelAnimationFrame(self.rAF);self.paused=false;delete self.startTime;self.startVal=self.frameVal;self.endVal=newEndVal;self.countDown=(self.startVal>self.endVal);self.rAF=requestAnimationFrame(self.count)};if(self.initialize()){self.printValue(self.startVal)}};


var options = {
    useEasing: true, // 使用緩和
    useGrouping: false, // 使用分組(是否顯示千位分隔符,一般為 true)
    separator: ',', // 分隔器(千位分隔符,默認為',')
    decimal: '.', // 十進製(小數點符號,默認為 '.')
    prefix: '', // 字首(數字的前墜,根據需要可設為 $,¥,￥ 等)
    suffix: '+' // 後墜(數字的後墜,根據需要可設為 元,個,美元 等)
};

// target,startVal,endVal,decimals,duration,options
// dom節點, 初始值,  結束值, 小數位數, 過渡幾秒 , 初始參數

// 檢查是否已觸發動畫
var hasAnimated = false;

// 獲取目標元素
var targetElement1 = document.getElementById('num1');
var targetElement2 = document.getElementById('num2');
var targetElement3 = document.getElementById('num3');
var targetElement4 = document.getElementById('num4');

// 監聽滾動事件
window.addEventListener('scroll', function() {

    // 檢查目標元素是否在可視範圍內
    var isInViewport = function(element) {
        var rect = element.getBoundingClientRect();
        return (
            rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    };

    // 如果目標元素在可視範圍內且尚未觸發動畫，則執行 CountUp.js 動畫
    if (isInViewport(targetElement1, targetElement2, targetElement3, targetElement4) && !hasAnimated) {
        var num1 = new CountUp('num1', 0, 123, 0, 5, options); // 使用 CountUp.js 初始化
        var num2 = new CountUp('num2', 0, 45, 0, 5, options);
        var num3 = new CountUp('num3', 0, 67, 0, 5, options);
        var num4 = new CountUp('num4', 0, 89, 0, 5, options);

        if (!num1.error || !num2.error || !num3.error || !num4.error) {
            num1.start();
            num2.start();
            num3.start();
            num4.start();
            hasAnimated = true; // 標記動畫已觸發
        } else {
            console.error(num1.error, num2.error, num3.error, num4.error);
        }
    }
});