(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a40ea28c"],{"0c18":function(t,e,i){},"2a17":function(t,e,i){},"3a66":function(t,e,i){"use strict";i.d(e,"a",(function(){return a}));var n=i("fe6c"),s=i("58df");function a(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];return Object(s["a"])(Object(n["b"])(["absolute","fixed"])).extend({name:"applicationable",props:{app:Boolean},computed:{applicationProperty:function(){return t}},watch:{app:function(t,e){e?this.removeApplication(!0):this.callUpdate()},applicationProperty:function(t,e){this.$vuetify.application.unregister(this._uid,e)}},activated:function(){this.callUpdate()},created:function(){for(var t=0,i=e.length;t<i;t++)this.$watch(e[t],this.callUpdate);this.callUpdate()},mounted:function(){this.callUpdate()},deactivated:function(){this.removeApplication()},destroyed:function(){this.removeApplication()},methods:{callUpdate:function(){this.app&&this.$vuetify.application.register(this._uid,this.applicationProperty,this.updateApplication())},removeApplication:function(){var t=arguments.length>0&&void 0!==arguments[0]&&arguments[0];(t||this.app)&&this.$vuetify.application.unregister(this._uid,this.applicationProperty)},updateApplication:function(){return 0}}})}},"4bd4":function(t,e,i){"use strict";i("4de4"),i("7db0"),i("4160"),i("caad"),i("07ac"),i("2532"),i("159b");var n=i("5530"),s=i("58df"),a=i("7e2b"),r=i("3206");e["a"]=Object(s["a"])(a["a"],Object(r["b"])("form")).extend({name:"v-form",provide:function(){return{form:this}},inheritAttrs:!1,props:{disabled:Boolean,lazyValidation:Boolean,readonly:Boolean,value:Boolean},data:function(){return{inputs:[],watchers:[],errorBag:{}}},watch:{errorBag:{handler:function(t){var e=Object.values(t).includes(!0);this.$emit("input",!e)},deep:!0,immediate:!0}},methods:{watchInput:function(t){var e=this,i=function(t){return t.$watch("hasError",(function(i){e.$set(e.errorBag,t._uid,i)}),{immediate:!0})},n={_uid:t._uid,valid:function(){},shouldValidate:function(){}};return this.lazyValidation?n.shouldValidate=t.$watch("shouldValidate",(function(s){s&&(e.errorBag.hasOwnProperty(t._uid)||(n.valid=i(t)))})):n.valid=i(t),n},validate:function(){return 0===this.inputs.filter((function(t){return!t.validate(!0)})).length},reset:function(){this.inputs.forEach((function(t){return t.reset()})),this.resetErrorBag()},resetErrorBag:function(){var t=this;this.lazyValidation&&setTimeout((function(){t.errorBag={}}),0)},resetValidation:function(){this.inputs.forEach((function(t){return t.resetValidation()})),this.resetErrorBag()},register:function(t){this.inputs.push(t),this.watchers.push(this.watchInput(t))},unregister:function(t){var e=this.inputs.find((function(e){return e._uid===t._uid}));if(e){var i=this.watchers.find((function(t){return t._uid===e._uid}));i&&(i.valid(),i.shouldValidate()),this.watchers=this.watchers.filter((function(t){return t._uid!==e._uid})),this.inputs=this.inputs.filter((function(t){return t._uid!==e._uid})),this.$delete(this.errorBag,e._uid)}}},render:function(t){var e=this;return t("form",{staticClass:"v-form",attrs:Object(n["a"])({novalidate:!0},this.attrs$),on:{submit:function(t){return e.$emit("submit",t)}}},this.$slots.default)}})},"615b":function(t,e,i){},7527:function(t,e,i){"use strict";var n=i("2a17"),s=i.n(n);s.a},"99d9":function(t,e,i){"use strict";i.d(e,"a",(function(){return a})),i.d(e,"b",(function(){return r})),i.d(e,"c",(function(){return o})),i.d(e,"d",(function(){return c}));var n=i("b0af"),s=i("80d2"),a=Object(s["h"])("v-card__actions"),r=Object(s["h"])("v-card__subtitle"),o=Object(s["h"])("v-card__text"),c=Object(s["h"])("v-card__title");n["a"]},a55b:function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("transition",{attrs:{name:"fade"}},[t.ok?i("div",{attrs:{id:"app"}},[i("v-app",{attrs:{dark:""}},[i("v-content",[i("v-container",{attrs:{"fill-height":""}},[i("v-layout",{attrs:{"align-center":"","justify-center":""}},[i("v-flex",{staticClass:"login-form text-xs-center"},[i("div",{staticClass:"display-1 mb-3"},[i("v-icon",{staticClass:"mr-2",attrs:{large:"large"}},[t._v(" mdi-bank-outline")]),t._v(" "),i("h3",{staticStyle:{display:"inline-block"}},[t._v("文物信息管理系统")])],1),i("v-card",{attrs:{light:"light"}},[t.loading?i("v-progress-linear",{attrs:{indeterminate:"",color:"cyan"}}):t._e(),i("v-card-text",[i("div",{staticClass:"subheading"},[i("transition",{attrs:{name:"fade"}},[t.options.errorAlert?i("v-alert",{attrs:{type:"error"}},[t._v(" 登录错误 ")]):t._e()],1),i("transition",{attrs:{name:"fade"}},[t.options.successAlert?i("v-alert",{attrs:{type:"success"}},[t._v(" 操作成功 ")]):t._e()],1),t.options.isLoggingIn?[t._v("登录")]:[t._v("注册")]],2),i("v-form",[i("v-text-field",{attrs:{light:"light","prepend-icon":"mdi-account",label:"用户名"},model:{value:t.user.name,callback:function(e){t.$set(t.user,"name",e)},expression:"user.name"}}),t.options.isLoggingIn?t._e():i("v-text-field",{attrs:{light:"light","prepend-icon":"mdi-account",label:"真名"},model:{value:t.user.realName,callback:function(e){t.$set(t.user,"realName",e)},expression:"user.realName"}}),t.options.isLoggingIn?t._e():i("v-text-field",{attrs:{type:"number",light:"light","prepend-icon":"mdi-phone",label:"电话"},model:{value:t.user.cell,callback:function(e){t.$set(t.user,"cell",e)},expression:"user.cell"}}),i("v-text-field",{attrs:{light:"light","prepend-icon":"mdi-lock",label:"密码",type:"password"},model:{value:t.user.password,callback:function(e){t.$set(t.user,"password",e)},expression:"user.password"}}),t.options.isLoggingIn?i("v-btn",{attrs:{block:"block",type:"submit",disabled:t.loading},on:{click:[function(t){t.preventDefault()},t.login]}},[t._v("登录")]):i("v-btn",{attrs:{block:"block",type:"submit",disabled:t.loading},on:{click:[function(e){e.preventDefault(),t.options.isLoggingIn=!0},t.register]}},[t._v("注册")])],1)],1)],1),t.options.isLoggingIn?i("div",[t._v("没有账号?点此注册 "),i("v-btn",{attrs:{light:"light",disabled:t.loading},on:{click:function(e){t.options.isLoggingIn=!1}}},[t._v("注册")])],1):t._e()],1)],1)],1)],1),i("v-footer",{attrs:{app:"app"}},[i("v-flex",{staticClass:"text-xs-center"},[t._v("© 2018. 保留所有权利.")])],1)],1)],1):t._e()])},s=[],a=(i("b0c0"),i("96cf"),i("1da1")),r=i("2b0e"),o=i("9715"),c=i("d4ec"),l=function t(e,i,n){Object(c["a"])(this,t),this.verifiable=[],this.info=e,this.name=i,this.type=n,this.verificationProcesses=[]},u=function t(e,i){Object(c["a"])(this,t),this.cell=e,this.realName=i},d=function t(e,i,n,s,a){Object(c["a"])(this,t),this.password=e,this.user=new l(new u(s,a),i,n)},h=(i("fa64"),i("afbc")),p=i("8d8d"),f=r["a"].extend({name:"login",components:{},data:function(){return{user:{type:"INDIVIDUAL"},options:{isLoggingIn:!0,errorAlert:!1,successAlert:!1},ok:!1,loading:!1}},beforeMount:function(){null!=this.$store.state.token?h["a"].go(-1):this.ok=!0},methods:{login:function(){var t=Object(a["a"])(regeneratorRuntime.mark((function t(){var e;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.prev=0,this.loading=!0,t.next=4,o["a"].login(this.user.name,this.user.password);case 4:return e=t.sent,this.$store.commit("login",e.data),p["a"].defaults.headers.common["x-api-key"]=e.data,localStorage.setItem("token",e.data),t.next=10,this.$store.dispatch("getUserObj");case 10:h["a"].go(-1),t.next=16;break;case 13:t.prev=13,t.t0=t["catch"](0),this.options.errorAlert=!0;case 16:return t.prev=16,this.loading=!1,t.finish(16);case 19:case"end":return t.stop()}}),t,this,[[0,13,16,19]])})));function e(){return t.apply(this,arguments)}return e}(),register:function(){var t=Object(a["a"])(regeneratorRuntime.mark((function t(){return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.prev=0,this.loading=!0,t.next=4,o["a"].register(new d(this.user.password,this.user.name,this.user.type,this.user.cell,this.user.realName));case 4:t.sent,this.successAlert=!0,h["a"].go(-1),t.next=12;break;case 9:t.prev=9,t.t0=t["catch"](0),this.errorAlert=!0;case 12:return t.prev=12,this.loading=!1,t.finish(12);case 15:case"end":return t.stop()}}),t,this,[[0,9,12,15]])})));function e(){return t.apply(this,arguments)}return e}()}}),v=f,g=(i("7527"),i("2877")),m=i("6544"),b=i.n(m),_=(i("caad"),i("5530")),y=i("ade3"),x=(i("0c18"),i("10d2")),$=i("afdd"),B=i("9d26"),w=i("f2e7"),O=i("7560"),k=r["a"].extend({name:"transitionable",props:{mode:String,origin:String,transition:String}}),C=i("58df"),j=i("d9bd"),I=Object(C["a"])(x["a"],w["a"],k).extend({name:"v-alert",props:{border:{type:String,validator:function(t){return["top","right","bottom","left"].includes(t)}},closeLabel:{type:String,default:"$vuetify.close"},coloredBorder:Boolean,dense:Boolean,dismissible:Boolean,closeIcon:{type:String,default:"$cancel"},icon:{default:"",type:[Boolean,String],validator:function(t){return"string"===typeof t||!1===t}},outlined:Boolean,prominent:Boolean,text:Boolean,type:{type:String,validator:function(t){return["info","error","success","warning"].includes(t)}},value:{type:Boolean,default:!0}},computed:{__cachedBorder:function(){if(!this.border)return null;var t={staticClass:"v-alert__border",class:Object(y["a"])({},"v-alert__border--".concat(this.border),!0)};return this.coloredBorder&&(t=this.setBackgroundColor(this.computedColor,t),t.class["v-alert__border--has-color"]=!0),this.$createElement("div",t)},__cachedDismissible:function(){var t=this;if(!this.dismissible)return null;var e=this.iconColor;return this.$createElement($["a"],{staticClass:"v-alert__dismissible",props:{color:e,icon:!0,small:!0},attrs:{"aria-label":this.$vuetify.lang.t(this.closeLabel)},on:{click:function(){return t.isActive=!1}}},[this.$createElement(B["a"],{props:{color:e}},this.closeIcon)])},__cachedIcon:function(){return this.computedIcon?this.$createElement(B["a"],{staticClass:"v-alert__icon",props:{color:this.iconColor}},this.computedIcon):null},classes:function(){var t=Object(_["a"])(Object(_["a"])({},x["a"].options.computed.classes.call(this)),{},{"v-alert--border":Boolean(this.border),"v-alert--dense":this.dense,"v-alert--outlined":this.outlined,"v-alert--prominent":this.prominent,"v-alert--text":this.text});return this.border&&(t["v-alert--border-".concat(this.border)]=!0),t},computedColor:function(){return this.color||this.type},computedIcon:function(){return!1!==this.icon&&("string"===typeof this.icon&&this.icon?this.icon:!!["error","info","success","warning"].includes(this.type)&&"$".concat(this.type))},hasColoredIcon:function(){return this.hasText||Boolean(this.border)&&this.coloredBorder},hasText:function(){return this.text||this.outlined},iconColor:function(){return this.hasColoredIcon?this.computedColor:void 0},isDark:function(){return!(!this.type||this.coloredBorder||this.outlined)||O["a"].options.computed.isDark.call(this)}},created:function(){this.$attrs.hasOwnProperty("outline")&&Object(j["a"])("outline","outlined",this)},methods:{genWrapper:function(){var t=[this.$slots.prepend||this.__cachedIcon,this.genContent(),this.__cachedBorder,this.$slots.append,this.$scopedSlots.close?this.$scopedSlots.close({toggle:this.toggle}):this.__cachedDismissible],e={staticClass:"v-alert__wrapper"};return this.$createElement("div",e,t)},genContent:function(){return this.$createElement("div",{staticClass:"v-alert__content"},this.$slots.default)},genAlert:function(){var t={staticClass:"v-alert",attrs:{role:"alert"},class:this.classes,style:this.styles,directives:[{name:"show",value:this.isActive}]};if(!this.coloredBorder){var e=this.hasText?this.setTextColor:this.setBackgroundColor;t=e(this.computedColor,t)}return this.$createElement("div",t,[this.genWrapper()])},toggle:function(){this.isActive=!this.isActive}},render:function(t){var e=this.genAlert();return this.transition?t("transition",{props:{name:this.transition,origin:this.origin,mode:this.mode}},[e]):e}}),A=i("7496"),V=i("8336"),E=i("b0af"),S=i("99d9"),L=i("a523"),P=(i("bd0c"),i("d10f")),N=P["a"].extend({name:"v-main",props:{tag:{type:String,default:"main"}},computed:{styles:function(){var t=this.$vuetify.application,e=t.bar,i=t.top,n=t.right,s=t.footer,a=t.insetFooter,r=t.bottom,o=t.left;return{paddingTop:"".concat(i+e,"px"),paddingRight:"".concat(n,"px"),paddingBottom:"".concat(s+a+r,"px"),paddingLeft:"".concat(o,"px")}}},render:function(t){var e={staticClass:"v-main",style:this.styles,ref:"main"};return t(this.tag,e,[t("div",{staticClass:"v-main__wrap"},this.$slots.default)])}}),D=N.extend({name:"v-main",created:function(){Object(j["d"])("v-content","v-main",this)},render:function(t){var e=N.options.render.call(this,t);return e.data.staticClass+=" v-content",e.children[0].data.staticClass+=" v-content__wrap",t(e.tag,e.data,e.children)}}),T=(i("20f6"),i("e8f2")),R=Object(T["a"])("flex"),U=(i("a9e3"),i("c7cd"),i("b5b6"),i("8dd9")),F=i("3a66"),z=i("80d2"),H=Object(C["a"])(U["a"],Object(F["a"])("footer",["height","inset"]),P["a"]).extend({name:"v-footer",props:{height:{default:"auto",type:[Number,String]},inset:Boolean,padless:Boolean,tag:{type:String,default:"footer"}},computed:{applicationProperty:function(){return this.inset?"insetFooter":"footer"},classes:function(){return Object(_["a"])(Object(_["a"])({},U["a"].options.computed.classes.call(this)),{},{"v-footer--absolute":this.absolute,"v-footer--fixed":!this.absolute&&(this.app||this.fixed),"v-footer--padless":this.padless,"v-footer--inset":this.inset})},computedBottom:function(){if(this.isPositioned)return this.app?this.$vuetify.application.bottom:0},computedLeft:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.left:0},computedRight:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.right:0},isPositioned:function(){return Boolean(this.absolute||this.fixed||this.app)},styles:function(){var t=parseInt(this.height);return Object(_["a"])(Object(_["a"])({},U["a"].options.computed.styles.call(this)),{},{height:isNaN(t)?t:Object(z["g"])(t),left:Object(z["g"])(this.computedLeft),right:Object(z["g"])(this.computedRight),bottom:Object(z["g"])(this.computedBottom)})}},methods:{updateApplication:function(){var t=parseInt(this.height);return isNaN(t)?this.$el?this.$el.clientHeight:0:t}},render:function(t){var e=this.setBackgroundColor(this.color,{staticClass:"v-footer",class:this.classes,style:this.styles});return t(this.tag,e,this.$slots.default)}}),J=i("4bd4"),M=i("132d"),W=Object(T["a"])("layout"),q=i("8e36"),Q=i("8654"),G=Object(g["a"])(v,n,s,!1,null,"91493ad6",null);e["default"]=G.exports;b()(G,{VAlert:I,VApp:A["a"],VBtn:V["a"],VCard:E["a"],VCardText:S["c"],VContainer:L["a"],VContent:D,VFlex:R,VFooter:H,VForm:J["a"],VIcon:M["a"],VLayout:W,VProgressLinear:q["a"],VTextField:Q["a"]})},afdd:function(t,e,i){"use strict";var n=i("8336");e["a"]=n["a"]},b0af:function(t,e,i){"use strict";i("0481"),i("4069"),i("a9e3");var n=i("5530"),s=(i("615b"),i("10d2")),a=i("297c"),r=i("1c87"),o=i("58df");e["a"]=Object(o["a"])(a["a"],r["a"],s["a"]).extend({name:"v-card",props:{flat:Boolean,hover:Boolean,img:String,link:Boolean,loaderHeight:{type:[Number,String],default:4},raised:Boolean},computed:{classes:function(){return Object(n["a"])(Object(n["a"])({"v-card":!0},r["a"].options.computed.classes.call(this)),{},{"v-card--flat":this.flat,"v-card--hover":this.hover,"v-card--link":this.isClickable,"v-card--loading":this.loading,"v-card--disabled":this.disabled,"v-card--raised":this.raised},s["a"].options.computed.classes.call(this))},styles:function(){var t=Object(n["a"])({},s["a"].options.computed.styles.call(this));return this.img&&(t.background='url("'.concat(this.img,'") center center / cover no-repeat')),t}},methods:{genProgress:function(){var t=a["a"].options.methods.genProgress.call(this);return t?this.$createElement("div",{staticClass:"v-card__progress",key:"progress"},[t]):null}},render:function(t){var e=this.generateRouteLink(),i=e.tag,n=e.data;return n.style=this.styles,this.isClickable&&(n.attrs=n.attrs||{},n.attrs.tabindex=0),t(i,this.setBackgroundColor(this.color,n),[this.genProgress(),this.$slots.default])}})},b5b6:function(t,e,i){},bd0c:function(t,e,i){},d10f:function(t,e,i){"use strict";var n=i("2b0e");e["a"]=n["a"].extend({name:"ssr-bootable",data:function(){return{isBooted:!1}},mounted:function(){var t=this;window.requestAnimationFrame((function(){t.$el.setAttribute("data-booted","true"),t.isBooted=!0}))}})},fa64:function(t,e,i){"use strict";var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("v-select",{attrs:{items:t.items,label:"用户类型"},on:{change:function(e){return t.$emit("change",e)}},model:{value:t.roleValue,callback:function(e){t.roleValue=e},expression:"roleValue"}})},s=[],a={name:"RoleSelector",props:{role:String},data:function(){return{roleValue:void 0,items:[{text:"个人",value:"INDIVIDUAL"},{text:"拍卖行",value:"AUCTIONEER"},{text:"文物店",value:"ANTIQUE_STORE"},{text:"文物部门",value:"ARCH_DEPT"},{text:"司法部门",value:"JUDICIAL_DEPT"},{text:"管理员",value:"ADMIN"}]}},mounted:function(){this.roleValue=this.role}},r=a,o=i("2877"),c=i("6544"),l=i.n(c),u=i("b974"),d=Object(o["a"])(r,n,s,!1,null,"2035dfef",null);e["a"]=d.exports;l()(d,{VSelect:u["a"]})}}]);
//# sourceMappingURL=chunk-a40ea28c.148ec5a3.js.map