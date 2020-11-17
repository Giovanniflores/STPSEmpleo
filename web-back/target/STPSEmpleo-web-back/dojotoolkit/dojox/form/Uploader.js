//>>built
define("dojox/form/Uploader",["dojo/_base/kernel","dojo/_base/declare","dojo/_base/lang","dojo/_base/array","dojo/_base/connect","dojo/_base/window","dojo/dom-style","dojo/dom-class","dojo/dom-geometry","dojo/dom-attr","dojo/dom-construct","dojo/dom-form","dijit","dijit/form/Button","./uploader/_Base","./uploader/_HTML5","./uploader/_IFrame","./uploader/_Flash","dojo/i18n!./nls/Uploader","dojo/text!./resources/Uploader.html"],function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_a,_b,_c,_d,_e,_f,_10,_11,_12,res,_13){
return _2("dojox.form.Uploader",[_f,_e,_10,_11,_12],{uploadOnSelect:false,tabIndex:0,multiple:false,label:res.label,url:"",name:"uploadedfile",flashFieldName:"",force:"",uploadType:"",showInput:"",focusedClass:"dijitButtonHover",_nameIndex:0,templateString:_13,baseClass:"dijitUploader "+_e.prototype.baseClass,postMixInProperties:function(){
this._inputs=[];
this._cons=[];
this.force=this.force.toLowerCase();
if(this.supports("multiple")){
this.uploadType=this.force==="form"?"form":"html5";
}else{
this.uploadType=this.force==="flash"?"flash":"iframe";
}
this.inherited(arguments);
},buildRendering:function(){
this.inherited(arguments);
_7.set(this.domNode,{overflow:"hidden",position:"relative"});
this._buildDisplay();
_a.set(this.titleNode,"tabIndex",-1);
},_buildDisplay:function(){
if(this.showInput){
this.displayInput=_b.create("input",{"class":"dijitUploadDisplayInput","tabIndex":-1,"autocomplete":"off","role":"presentation"},this.containerNode,this.showInput);
this._attachPoints.push("displayInput");
this.connect(this,"onChange",function(_14){
var i=0,l=_14.length,f,r=[];
while((f=_14[i++])){
if(f&&f.name){
r.push(f.name);
}
}
this.displayInput.value=r.join(", ");
});
this.connect(this,"reset",function(){
this.displayInput.value="";
});
}
},startup:function(){
if(this._buildInitialized){
return;
}
this._buildInitialized=true;
this._getButtonStyle(this.domNode);
this._setButtonStyle();
this.inherited(arguments);
},onChange:function(_15){
},onBegin:function(_16){
},onProgress:function(_17){
},onComplete:function(_18){
this.reset();
},onCancel:function(){
},onAbort:function(){
},onError:function(_19){
},upload:function(_1a){
_1a=_1a||{};
_1a.uploadType=this.uploadType;
this.inherited(arguments);
},submit:function(_1b){
_1b=!!_1b?_1b.tagName?_1b:this.getForm():this.getForm();
var _1c=_c.toObject(_1b);
_1c.uploadType=this.uploadType;
this.upload(_1c);
},reset:function(){
delete this._files;
this._disconnectButton();
_4.forEach(this._inputs,_b.destroy,dojo);
this._inputs=[];
this._nameIndex=0;
this._createInput();
},getFileList:function(){
var _1d=[];
if(this.supports("multiple")){
_4.forEach(this._files,function(f,i){
_1d.push({index:i,name:f.name,size:f.size,type:f.type});
},this);
}else{
_4.forEach(this._inputs,function(n,i){
if(n.value){
_1d.push({index:i,name:n.value.substring(n.value.lastIndexOf("\\")+1),size:0,type:n.value.substring(n.value.lastIndexOf(".")+1)});
}
},this);
}
return _1d;
},_getValueAttr:function(){
return this.getFileList();
},_setValueAttr:function(_1e){
console.error("Uploader value is read only");
},_setDisabledAttr:function(_1f){
if(this.disabled==_1f||!this.inputNode){
return;
}
this.inherited(arguments);
_7.set(this.inputNode,"display",_1f?"none":"");
},_getButtonStyle:function(_20){
this.btnSize={w:_7.get(_20,"width"),h:_7.get(_20,"height")};
},_setButtonStyle:function(){
this.inputNodeFontSize=Math.max(2,Math.max(Math.ceil(this.btnSize.w/60),Math.ceil(this.btnSize.h/15)));
this._createInput();
},_getFileFieldName:function(){
var _21;
if(this.supports("multiple")&&this.multiple){
_21=this.name+"s[]";
}else{
_21=this.name+(this.multiple?this._nameIndex:"");
}
return _21;
},_createInput:function(){
if(this._inputs.length){
_7.set(this.inputNode,{top:"500px"});
this._disconnectButton();
this._nameIndex++;
}
var _22=this._getFileFieldName();
this.focusNode=this.inputNode=_b.create("input",{type:"file",name:_22,"aria-labelledby":this.id+"_label"},this.domNode,"first");
if(this.supports("multiple")&&this.multiple){
_a.set(this.inputNode,"multiple",true);
}
this._inputs.push(this.inputNode);
_7.set(this.inputNode,{position:"absolute",fontSize:this.inputNodeFontSize+"em",top:"-3px",right:"-3px",opacity:0});
this._connectButton();
},_connectButton:function(){
this._cons.push(_5.connect(this.inputNode,"change",this,function(evt){
this._files=this.inputNode.files;
this.onChange(this.getFileList(evt));
if(!this.supports("multiple")&&this.multiple){
this._createInput();
}
}));
if(this.tabIndex>-1){
this.inputNode.tabIndex=this.tabIndex;
this._cons.push(_5.connect(this.inputNode,"focus",this,function(){
_8.add(this.domNode,this.focusedClass);
}));
this._cons.push(_5.connect(this.inputNode,"blur",this,function(){
_8.remove(this.domNode,this.focusedClass);
}));
}
},_disconnectButton:function(){
_4.forEach(this._cons,_5.disconnect);
this._cons.splice(0,this._cons.length);
}});
});
require({cache:{"url:dojox/form/resources/Uploader.html":"<span class=\"dijit dijitReset dijitInline\"\n\t><span class=\"dijitReset dijitInline dijitButtonNode\"\n\t\tdata-dojo-attach-event=\"ondijitclick:_onClick\"\n\t\t><span class=\"dijitReset dijitStretch dijitButtonContents\"\n\t\t\tdata-dojo-attach-point=\"titleNode,focusNode\"\n\t\t\trole=\"button\" aria-labelledby=\"${id}_label\"\n\t\t\t><span class=\"dijitReset dijitInline dijitIcon\" data-dojo-attach-point=\"iconNode\"></span\n\t\t\t><span class=\"dijitReset dijitToggleButtonIconChar\">&#x25CF;</span\n\t\t\t><span class=\"dijitReset dijitInline dijitButtonText\"\n\t\t\t\tid=\"${id}_label\"\n\t\t\t\tdata-dojo-attach-point=\"containerNode\"\n\t\t\t></span\n\t\t></span\n\t></span\n\t> \n\t<input ${!nameAttrSetting} type=\"${type}\" value=\"${value}\" class=\"dijitOffScreen\" tabIndex=\"-1\" data-dojo-attach-point=\"valueNode\" />\n</span>\n"}});
