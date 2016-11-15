/* ! FullCalendar v2.9.0 Docs & License: http://fullcalendar.io/ (c) 2016 Adam
 * Shaw
 */
!function (a) {
    "function" == typeof define && define.amd
        ? define([
            "jquery", "moment"
        ], a)
        : "object" == typeof exports
            ? module.exports = a(require("jquery"), require("moment"))
            : a(jQuery, moment)
}(function (a, b) {
    function c(a) {
        return W(a, Ya)
    }
    function d(b) {
        var c,
            d = {
                views: b.views || {}
            };
        return a.each(b, function (b, e) {
            "views" != b && (a.isPlainObject(e) && !/(time|duration|interval)$/i.test(b) && -1 == a.inArray(b, Ya)
                ? (c = null, a.each(e, function (a, e) {
                    /^(month|week|day|default|basic(Week|Day)?|agenda(Week|Day)?)$/.test(a)
                        ? (d.views[a] || (d.views[a] = {}), d.views[a][b] = e)
                        : (c || (c = {}), c[a] = e)
                }), c && (d[b] = c))
                : d[b] = e)
        }),
        d
    } 
    function e(a, b) {
        b.left && a.css({
            "border-left-width": 1,
            "margin-left"      : b.left - 1
        }),
        b.right && a.css({
            "border-right-width": 1,
            "margin-right"      : b.right - 1
        })
    }
    function f(a) {
        a.css({"border-left-width": "", "border-right-width": "", "margin-left": "", "margin-right": ""})
    }
    function g() {
        a("body").addClass("fc-not-allowed")
    }
    function h() {
        a("body").removeClass("fc-not-allowed")
    }
    function i(b, c, d) {
        var e = Math.floor(c / b.length),
            f = Math.floor(c - e * (b.length - 1)),
            g = [],
            h = [],
            i = [],
            k = 0;
        j(b),
        b.each(function (c, d) {
            var j = c === b.length - 1
                    ? f
                    : e,
                l = a(d).outerHeight(!0);
            j > l
                ? (g.push(d), h.push(l), i.push(a(d).height()))
                : k += l
        }),
        d && (c -= k, e = Math.floor(c / g.length), f = Math.floor(c - e * (g.length - 1))),
        a(g).each(function (b, c) {
            var d = b === g.length - 1
                    ? f
                    : e,
                j = h[b],
                k = i[b],
                l = d - (j - k);
            d > j && a(c).height(l)
        })
    }
    function j(a) {
        a.height("")
    }
    function k(b) {
        var c = 0;
        return b
            .find("> span")
            .each(function (b, d) {
                var e = a(d).outerWidth();
                e > c && (c = e)
            }),
        c++,
        b.width(c),
        c
    }
    function l(a, b) {
        var c,
            d = a.add(b);
        return d.css({left: -1, position: "relative"}),
        c = a.outerHeight() - b.outerHeight(),
        d.css({left: "", position: ""}),
        c
    }
    function m(b) {
        var c = b.css("position"),
            d = b
                .parents()
                .filter(function () {
                    var b = a(this);
                    return /(auto|scroll)/.test(b.css("overflow") + b.css("overflow-y") + b.css("overflow-x"))
                })
                .eq(0);
        return "fixed" !== c && d.length
            ? d
            : a(b[0].ownerDocument || document)
    }
    function n(a, b) {
        var c = a.offset(),
            d = c.left - (b
                ? b.left
                : 0),
            e = c.top - (b
                ? b.top
                : 0);
        return {
            bottom: e + a.outerHeight(),
            left  : d,
            right : d + a.outerWidth(),
            top   : e
        }
    }
    function o(a, b) {
        var c = a.offset(),
            d = q(a),
            e = c.left + t(a, "border-left-width") + d.left - (b
                ? b.left
                : 0),
            f = c.top + t(a, "border-top-width") + d.top - (b
                ? b.top
                : 0);
        return {
            bottom: f + a[0].clientHeight,
            left  : e,
            right : e + a[0].clientWidth,
            top   : f
        }
    }
    function p(a, b) {
        var c = a.offset(),
            d = c.left + t(a, "border-left-width") + t(a, "padding-left") - (b
                ? b.left
                : 0),
            e = c.top + t(a, "border-top-width") + t(a, "padding-top") - (b
                ? b.top
                : 0);
        return {
            bottom: e + a.height(),
            left  : d,
            right : d + a.width(),
            top   : e
        }
    }
    function q(a) {
        var b = a.innerWidth() - a[0].clientWidth,
            c = {
                bottom: a.innerHeight() - a[0].clientHeight,
                left  : 0,
                right : 0,
                top   : 0
            };
        return r() && "rtl" == a.css("direction")
            ? c.left = b
            : c.right = b,
        c
    }
    function r() {
        return null === Za && (Za = s()),
        Za
    }
    function s() {
        var b = a("<div><div/></div>")
                .css({
                border   : 0,
                direction: "rtl",
                left     : 0,
                overflow : "scroll",
                padding  : 0,
                position : "absolute",
                top      : -1e3
            })
                .appendTo("body"),
            c = b.children(),
            d = c
                .offset()
                .left > b
                .offset()
                .left;
        return b.remove(),
        d
    }
    function t(a, b) {
        return parseFloat(a.css(b)) || 0
    }
    function u(a) {
        return 1 == a.which && !a.ctrlKey
    }
    function v(a) {
        if (void 0 !== a.pageX) 
            return a.pageX;
        var b = a.originalEvent.touches;
        return b
            ? b[0].pageX
            : void 0
    }
    function w(a) {
        if (void 0 !== a.pageY) 
            return a.pageY;
        var b = a.originalEvent.touches;
        return b
            ? b[0].pageY
            : void 0
    }
    function x(a) {
        return /^touch/.test(a.type)
    }
    function y(a) {
        a
            .addClass("fc-unselectable")
            .on("selectstart", z)
    }
    function z(a) {
        a.preventDefault()
    }
    function A(a) {
        return window.addEventListener
            ? (window.addEventListener("scroll", a, !0), !0)
            : !1
    }
    function B(a) {
        return window.removeEventListener
            ? (window.removeEventListener("scroll", a, !0), !0)
            : !1
    }
    function C(a, b) {
        var c = {
            bottom: Math.min(a.bottom, b.bottom),
            left  : Math.max(a.left, b.left),
            right : Math.min(a.right, b.right),
            top   : Math.max(a.top, b.top)
        };
        return c.left < c.right && c.top < c.bottom
            ? c
            : !1
    }
    function D(a, b) {
        return {
            left: Math.min(Math.max(a.left, b.left), b.right),
            top : Math.min(Math.max(a.top, b.top), b.bottom)
        }
    }
    function E(a) {
        return {
            left: (a.left + a.right) / 2,
            top : (a.top + a.bottom) / 2
        }
    }
    function F(a, b) {
        return {
            left: a.left - b.left,
            top : a.top - b.top
        }
    }
    function G(b) {
        var c,
            d,
            e = [],
            f = [];
        for ("string" == typeof b
            ? f = b.split(/\s*,\s*/)
            : "function" == typeof b
                ? f = [b]
                : a.isArray(b) && (f = b), c = 0; c < f.length; c++) 
            d = f[c],
            "string" == typeof d
                ? e.push("-" == d.charAt(0)
                    ? {
                        field: d.substring(1),
                        order: -1
                    }
                    : {
                        field: d,
                        order: 1
                    })
                : "function" == typeof d && e.push({func: d});
        return e
    }
    function H(a, b, c) {
        var d,
            e;
        for (d = 0; d < c.length; d++) 
            if (e = I(a, b, c[d])) 
                return e;
    return 0
    }
    function I(a, b, c) {
        return c.func
            ? c.func(a, b)
            : J(a[c.field], b[c.field]) * (c.order || 1)
    }
    function J(b, c) {
        return b || c
            ? null == c
                ? -1
                : null == b
                    ? 1
                    : "string" === a.type(b) || "string" === a.type(c)
                        ? String(b).localeCompare(String(c))
                        : b - c
            : 0
    }
    function K(a, b) {
        var c,
            d,
            e,
            f,
            g = a.start,
            h = a.end,
            i = b.start,
            j = b.end;
        return h > i && j > g
            ? (g >= i
                ? (c = g.clone(), e = !0)
                : (c = i.clone(), e = !1), j >= h
                ? (d = h.clone(), f = !0)
                : (d = j.clone(), f = !1), {
                end    : d,
                isEnd  : f,
                isStart: e,
                start  : c
            })
            : void 0
    }
    function L(a, c) {
        return b.duration({
            days: a
                .clone()
                .stripTime()
                .diff(c.clone().stripTime(), "days"),
            ms  : a.time() - c.time()
        })
    }
    function M(a, c) {
        return b.duration({
            days: a
                .clone()
                .stripTime()
                .diff(c.clone().stripTime(), "days")
        })
    }
    function N(a, c, d) {
        return b.duration(Math.round(a.diff(c, d, !0)), d)
    }
    function O(a, b) {
        var c,
            d,
            e;
        for (c = 0; c < _a.length && (d = _a[c], e = P(d, a, b), !(e >= 1 && ha(e))); c++) 
        ;
        return d
    }
    function P(a, c, d) {
        return null != d
            ? d.diff(c, a, !0)
            : b.isDuration(c)
                ? c.as(a)
                : c
                    .end
                    .diff(c.start, a, !0)
    }
    function Q(a, b, c) {
        var d;
        return T(c)
            ? (b - a) / c
            : (d = c.asMonths(), Math.abs(d) >= 1 && ha(d)
                ? b.diff(a, "months", !0) / d
                : b.diff(a, "days", !0) / c.asDays())
    }
    function R(a, b) {
        var c,
            d;
        return T(a) || T(b)
            ? a / b
            : (c = a.asMonths(), d = b.asMonths(), Math.abs(c) >= 1 && ha(c) && Math.abs(d) >= 1 && ha(d)
                ? c / d
                : a.asDays() / b.asDays())
    }
    function S(a, c) {
        var d;
        return T(a)
            ? b.duration(a * c)
            : (d = a.asMonths(), Math.abs(d) >= 1 && ha(d)
                ? b.duration({months: *c})
                : b.duration({
                    days: a.asDays() * c
                }))
    }
    function T(a) {
        return Boolean(a.hours() || a.minutes() || a.seconds() || a.milliseconds())
    }
    function U(a) {
        return "[object Date]" === Object
            .prototype
            .toString
            .call(a) || a instanceof Date
    }
    function V(a) {
        return /^\d+\:\d+(?:\:\d+\.?(?:\d{3})?)?$/.test(a)
    }
    function W(a, b) {
        var c,
            d,
            e,
            f,
            g,
            h,
            i = {};
        if (b) 
            for (c = 0; c < b.length; c++) {
                for (d = b[c], e = [], f = a.length - 1; f >= 0; f--) 
                    if (g = a[f][d], "object" == typeof g) 
                        e.unshift(g);
                    else if (void 0 !== g) {
                        i[d] = g;
                        break
                    }
                e.length && (i[d] = W(e))
            }
        for (c = a.length - 1; c >= 0; c--) {
            h = a[c];
            for (d in h) 
                d in i || (i[d] = h[d])
        }
        return i
    }
    function X(a) {
        var b = function () {};
        return b.prototype = a,
        new b
    }
    function Y(a, b) {
        for (var c in a) 
            $(a, c) && (b[c] = a[c])
    }
    function Z(a, b) {
        var c,
            d,
            e = ["constructor", "toString", "valueOf"];
        for (c = 0; c < e.length; c++) 
            d = e[c],
            a[d] !== Object.prototype[d] && (b[d] = a[d])
    }
    function $(a, b) {
        return db.call(a, b)
    }
    function _(b) {
        return /undefined|null|boolean|number|string/.test(a.type(b))
    }
    function aa(b, c, d) {
        if (a.isFunction(b) && (b = [b]), b) {
            var e,
                f;
            for (e = 0; e < b.length; e++) 
                f = b[e].apply(c, d) || f;
            return f
        }
    }
    function ba() {
        for (var a = 0; a < arguments.length; a++) 
            if (void 0 !== arguments[a]) 
                return arguments[a]
    }
    function ca(a) {
        return (a + "")
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/'/g, "&#039;")
            .replace(/"/g, "&quot;")
            .replace(/\n/g, "<br />")
    }
    function da(a) {
        return a.replace(/&.*?;/g, "")
    }
    function ea(b) {
        var c = [];
        return a.each(b, function (a, b) {
            null != b && c.push(a + ":" + b)
        }),
        c.join(";")
    }
    function fa(a) {
        return a
            .charAt(0)
            .toUpperCase() + a.slice(1)
    }
    function ga(a, b) {
        return a - b
    }
    function ha(a) {
        return a % 1 === 0
    }
    function ia(a, b) {
        var c = a[b];
        return function () {
            return c.apply(a, arguments)
        }
    }
    function ja(a, b, c) {
        var d,
            e,
            f,
            g,
            h,
            i = function () {
                var j =+ new Date - g;
                b > j
                    ? d = setTimeout(i, b - j)
                    : (d = null, c || (h = a.apply(f, e), f = e = null))
            };
        return function () {
            f = this,
            e = arguments,
            g =+ new Date;
            var j = c && !d;
            return d || (d = setTimeout(i, b)),
            j && (h = a.apply(f, e), f = e = null),
            h
        }
    }
    function ka(b, c) {
        return b && b.then && "resolved" !== b.state()
            ? c
                ? b.then(c)
                : void 0
            : a.when(c())
    }
    function la(c, d, e) {
        var f,
            g,
            h,
            i,
            j = c[0],
            k = 1 == c.length && "string" == typeof j;
        return b.isMoment(j)
            ? (i = b.apply(null, c), na(j, i))
            : U(j) || void 0 === j
                ? i         = b.apply(null, c)
                : (f = !1, g = !1, k
                    ? eb.test(j)
                        ? (j += "-01", c = [j], f = !0, g = !0)
                        : (h = fb.exec(j)) && (f = !h[5], g = !0)
                    : a.isArray(j) && (g = !0), i = d || f
                    ? b.utc.apply(b, c)
                    : b.apply(null, c), f
                    ? (i._ambigTime = !0, i._ambigZone = !0)
                    : e && (g
                        ? i._ambigZone = !0
                        : k && (i.utcOffset
                            ? i.utcOffset(j)
                            : i.zone(j)))),
        i._fullCalendar = !0,
        i
    }
    function ma(a, c) {
        var d,
            e,
            f = !1,
            g = !1,
            h = a.length,
            i = [];
        for (d = 0; h > d; d++) 
            e = a[d],
            b.isMoment(e) || (e = Wa.moment.parseZone(e)),
            f = f || e._ambigTime,
            g = g || e._ambigZone,
            i.push(e);
        for (d = 0; h > d; d++) 
            e = i[d],
            c || !f || e._ambigTime
                ? g && !e._ambigZone && (i[d] = e.clone().stripZone())
                : i[d] = e.clone().stripTime();
        return i
    }
    function na(a, b) {
        a._ambigTime
            ? b._ambigTime = !0
            : b._ambigTime && (b._ambigTime = !1),
        a._ambigZone
            ? b._ambigZone = !0
            : b._ambigZone && (b._ambigZone = !1)
    }
    function oa(a, b) {
        a.year(b[0] || 0).month(b[1] || 0).date(b[2] || 0).hours(b[3] || 0).minutes(b[4] || 0).seconds(b[5] || 0).milliseconds(b[6] || 0)
    }
    function pa(a, b) {
        return hb
            .format
            .call(a, b)
    }
    function qa(a, b) {
        return ra(a, wa(b))
    }
    function ra(a, b) {
        var c,
            d = "";
        for (c = 0; c < b.length; c++) 
            d += sa(a, b[c]);
        return d
    }
    function sa(a, b) {
        var c,
            d;
        return "string" == typeof b
            ? b
            : (c = b.token)
                ? ib[c]
                    ? ib[c](a)
                    : pa(a, c)
                : b.maybe && (d = ra(a, b.maybe), d.match(/[1-9]/))
                    ? d
                    : ""
    }
    function ta(a, b, c, d, e) {
        var f;
        return a = Wa
            .moment
            .parseZone(a),
        b        = Wa
            .moment
            .parseZone(b),
        f        = (a.localeData || a.lang).call(a),
        c        = f.longDateFormat(c) || c,
        d        = d || " - ",
        ua(a, b, wa(c), d, e)
    }
    function ua(a, b, c, d, e) {
        var f,
            g,
            h,
            i,
            j = a
                .clone()
                .stripZone(),
            k = b
                .clone()
                .stripZone(),
            l = "",
            m = "",
            n = "",
            o = "",
            p = "";
        for (g = 0; g < c.length && (f = va(a, b, j, k, c[g]), f !== !1); g++) 
            l += f;
        for (h = c.length - 1; h > g && (f = va(a, b, j, k, c[h]), f !== !1); h--) 
            m = f + m;
        for (i = g; h >= i; i++) 
            n += sa(a, c[i]),
            o += sa(b, c[i]);
        return (n || o) && (p = e
            ? o + d + n
            : n + d + o),
        l + p + m
    }
    function va(a, b, c, d, e) {
        var f,
            g;
        return "string" == typeof e
            ? e
            : (f = e.token) && (g = jb[f.charAt(0)], g && c.isSame(d, g))
                ? pa(a, f)
                : !1
    }
    function wa(a) {
        return a in kb
            ? kb[a]
            : kb[a] = xa(a)
    }
    function xa(a) {
        for (var b, c = [], d = /\[([^\]]*)\]|\(([^\)]*)\)|(LTS|LT|(\w)\4*o?)|([^\w\[\(]+)/g; b = d.exec(a);) 
            b[1]
                ? c.push(b[1])
                : b[2]
                    ? c.push({
                        maybe: xa(b[2])
                    })
                    : b[3]
                        ? c.push({token: b[3]})
                        : b[5] && c.push(b[5]);
        return c
    }
    function ya() {}
    function za(a, b) {
        var c;
        return $(b, "constructor") && (c = b.constructor),
        "function" != typeof c && (c = b.constructor = function () {
            a.apply(this, arguments)
        }),
        c.prototype = X(a.prototype),
        Y(b, c.prototype),
        Z(b, c.prototype),
        Y(a, c),
        c
    }
    function Aa(a, b) {
        Y(b, a.prototype)
    }
    function Ba(a, b) {
        return a || b
            ? a && b
                ? a.component === b.component && Ca(a, b) && Ca(b, a)
                : !1
            : !0
    }
    function Ca(a, b) {
        for (var c in a) 
            if (!/^(component|left|right|top|bottom)$/.test(c) && a[c] !== b[c]) 
                return !1;
    return !0
    }
    function Da(a) {
        var b = Fa(a);
        return "background" === b || "inverse-background" === b
    }
    function Ea(a) {
        return "inverse-background" === Fa(a)
    }
    function Fa(a) {
        return ba((a.source || {}).rendering, a.rendering)
    }
    function Ga(a) {
        var b,
            c,
            d = {};
        for (b = 0; b < a.length; b++) 
            c = a[b],
            (d[c._id] || (d[c._id] = [])).push(c);
        return d
    }
    function Ha(a, b) {
        return a.start - b.start
    }
    function Ia(c) {
        var d,
            e,
            f,
            g,
            h = Wa.dataAttrPrefix;
        return h && (h += "-"),
        d = c.data(h + "event") || null,
        d && (d = "object" == typeof d
            ? a.extend({}, d)
            : {}, e = d.start, null == e && (e = d.time), f = d.duration, g = d.stick, delete d.start, delete d.time, delete d.duration, delete d.stick),
        null == e && (e = c.data(h + "start")),
        null == e && (e = c.data(h + "time")),
        null == f && (f = c.data(h + "duration")),
        null == g && (g = c.data(h + "stick")),
        e = null != e
            ? b.duration(e)
            : null,
        f = null != f
            ? b.duration(f)
            : null,
        g = Boolean(g), {
            duration  : f,
            eventProps: d,
            startTime : e,
            stick     : g
        }
    }
    function Ja(a, b) {
        var c,
            d;
        for (c = 0; c < b.length; c++) 
            if (d = b[c], d.leftCol <= a.rightCol && d.rightCol >= a.leftCol) 
                return !0;
    return !1
    }
    function Ka(a, b) {
        return a.leftCol - b.leftCol
    }
    function La(a) {
        var b,
            c,
            d,
            e = [];
        for (b = 0; b < a.length; b++) {
            for (c = a[b], d = 0; d < e.length && Oa(c, e[d]).length; d++) 
            ;
            c.level = d,
            (e[d] || (e[d] = [])).push(c)
        }
        return e
    }
    function Ma(a) {
        var b,
            c,
            d,
            e,
            f;
        for (b = 0; b < a.length; b++) 
            for (c = a[b], d = 0; d < c.length; d++) 
                for (e = c[d], e.forwardSegs = [], f = b + 1; f < a.length; f++) 
                    Oa(e, a[f], e.forwardSegs)
    }
    function Na(a) {
        var b,
            c,
            d = a.forwardSegs,
            e = 0;
        if (void 0 === a.forwardPressure) {
            for (b = 0; b < d.length; b++) 
                c = d[b],
                Na(c),
                e = Math.max(e, 1 + c.forwardPressure);
            a.forwardPressure = e
        }
    }
    function Oa(a, b, c) {
        c = c || [];
        for (var d = 0; d < b.length; d++) 
            Pa(a, b[d]) && c.push(b[d]);
        return c
    }
    function Pa(a, b) {
        return a.bottom > b.top && a.top < b.bottom
    }
    function Qa(c, e) {
        function f(a) {
            "_locale" in a
                ? a._locale = U
                : a._lang = U
        }
        function g() {
            W
                ? k() && (p(), l())
                : h()
        }
        function h() {
            c.addClass("fc"),
            T.bindOption("theme", function (a) {
                Y = a
                    ? "ui"
                    : "fc",
                c.toggleClass("ui-widget", a),
                c.toggleClass("fc-unthemed", !a)
            }),
            T.bindOptions([
                "isRTL", "lang"
            ], function (a) {
                c.toggleClass("fc-ltr", !a),
                c.toggleClass("fc-rtl", a)
            }),
            W = a("<div class='fc-view-container'/>").prependTo(c),
            V = T.header = new Ta(T),
            i(),
            l(T.options.defaultView),
            T.options.handleWindowResize && (_ = ja(r, T.options.windowResizeDelay), a(window).resize(_))
        }
        function i() {
            V.render(),
            V.el && c.prepend(V.el)
        }
        function j() {
            Z && Z.removeElement(),
            V.removeElement(),
            W.remove(),
            c.removeClass("fc fc-ltr fc-rtl fc-unthemed ui-widget"),
            _ && a(window).unbind("resize", _)
        }
        function k() {
            return c.is(":visible")
        }
        function l(b, c) {
            ga++,
            Z && b && Z.type !== b && (M(), m()),
            !Z && b && (Z = T.view = fa[b] || (fa[b] = T.instantiateView(b)), Z.setElement(a("<div class='fc-view fc-" + b + "-view' />").appendTo(W)), V.activateButton(b)),
            Z && (aa = Z.massageCurrentDate(aa), Z.displaying && aa.isWithin(Z.intervalStart, Z.intervalEnd) || k() && (Z.display(aa, c), N(), z(), A(), v())),
            N(),
            ga--
        }
        function m() {
            V.deactivateButton(Z.type),
            Z.removeElement(),
            Z = T.view = null
        }
        function n() {
            ga++,
            M();
            var a = Z.type,
                b = Z.queryScroll();
            m(),
            l(a, b),
            N(),
            ga--
        }
        function o(a) {
            return k()
                ? (a && q(), ga++, Z.updateSize(!0), ga--, !0)
                : void 0
        }
        function p() {
            k() && q()
        }
        function q() {
            $ = "number" == typeof T.options.contentHeight
                ? T.options.contentHeight
                : "number" == typeof T.options.height
                    ? T.options.height - (V.el
                        ? V.el.outerHeight(!0)
                        : 0)
                    : Math.round(W.width() / Math.max(T.options.aspectRatio, .5))
        }
        function r(a) {
            !ga && a.target === window && Z.start && o(!0) && Z.trigger("windowResize", ea)
        }
        function s() {
            w()
        }
        function t(a) {
            da(T.getEventSourcesByMatchArray(a))
        }
        function u() {
            k() && (M(), Z.displayEvents(ha), N())
        }
        function v() {
            !T.options.lazyFetching || ba(Z.start, Z.end)
                ? w()
                : u()
        }
        function w() {
            ca(Z.start, Z.end)
        }
        function x(a) {
            ha = a,
            u()
        }
        function y() {
            u()
        }
        function z() {
            V.updateTitle(Z.title)
        }
        function A() {
            var a = T.getNow();
            a.isWithin(Z.intervalStart, Z.intervalEnd)
                ? V.disableButton("today")
                : V.enableButton("today")
        }
        function B(a, b) {
            Z.select(T.buildSelectSpan.apply(T, arguments))
        }
        function C() {
            Z && Z.unselect()
        }
        function D() {
            aa = Z.computePrevDate(aa),
            l()
        }
        function E() {
            aa = Z.computeNextDate(aa),
            l()
        }
        function F() {
            aa.add(-1, "years"),
            l()
        }
        function G() {
            aa.add(1, "years"),
            l()
        }
        function H() {
            aa = T.getNow(),
            l()
        }
        function I(a) {
            aa = T
                .moment(a)
                .stripZone(),
            l()
        }
        function J(a) {
            aa.add(b.duration(a)),
            l()
        }
        function K(a, b) {
            var c;
            b  = b || "day",
            c  = T.getViewSpec(b) || T.getUnitViewSpec(b),
            aa = a.clone(),
            l(c
                ? c.type
                : null)
        }
        function L() {
            return T.applyTimezone(aa)
        }
        function M() {
            W.css({
                height  : W.height(),
                overflow: "hidden",
                width   : "100%"
            })
        }
        function N() {
            W.css({height: "", overflow: "", width: ""})
        }
        function O() {
            return T
        }
        function P() {
            return Z
        }
        function Q(a, b) {
            var c;
            if ("string" == typeof a) {
                if (void 0 === b) 
                    return T.options[a];
                c    = {},
                c[a] = b,
                R(c)
            } else 
                "object" == typeof a && R(a)
        }
        function R(a) {
            var b,
                c = 0;
            for (b in a) 
                T.dynamicOverrides[b] = a[b];
            T.viewSpecCache = {},
            T.populateOptionsHash();
            for (b in a) 
                T.triggerOptionHandlers(b),
                c++;
            if (1 === c) {
                if ("height" === b || "contentHeight" === b || "aspectRatio" === b) 
                    return void o(!0);
                if ("defaultDate" === b) 
                    return;
                if ("businessHours" === b) 
                    return void(Z && (Z.unrenderBusinessHours(), Z.renderBusinessHours()));
                if ("timezone" === b) 
                    return T.rezoneArrayEventSources(),
                    void s()
            }
            i(),
            fa = {},
            n()
        }
        function S(a, b) {
            var c = Array
                .prototype
                .slice
                .call(arguments, 2);
            return b = b || ea,
            this.triggerWith(a, b, c),
            T.options[a]
                ? T
                    .options[a]
                    .apply(b, c)
                : void 0
        }
        var T = this;
        T.render              = g,
        T.destroy             = j,
        T.refetchEvents       = s,
        T.refetchEventSources = t,
        T.reportEvents        = x,
        T.reportEventChange   = y,
        T.rerenderEvents      = u,
        T.changeView          = l,
        T.select              = B,
        T.unselect            = C,
        T.prev                = D,
        T.next                = E,
        T.prevYear            = F,
        T.nextYear            = G,
        T.today               = H,
        T.gotoDate            = I,
        T.incrementDate       = J,
        T.zoomTo              = K,
        T.getDate             = L,
        T.getCalendar         = O,
        T.getView             = P,
        T.option              = Q,
        T.trigger             = S,
        T.dynamicOverrides    = {},
        T.viewSpecCache       = {},
        T.optionHandlers      = {},
        T.overrides           = d(e || {}),
        T.populateOptionsHash();
        var U;
        T.bindOptions([
            "lang",
            "monthNames",
            "monthNamesShort",
            "dayNames",
            "dayNamesShort",
            "firstDay",
            "weekNumberCalculation"
        ], function (a, b, c, d, e, g, h) {
            if (U = X(Sa(a)), b && (U._months = b), c && (U._monthsShort = c), d && (U._weekdays = d), e && (U._weekdaysShort = e), null != g) {
                var i = X(U._week);
                i.dow   = g,
                U._week = i
            }
            "iso" === h && (h = "ISO"),
            "ISO" !== h && "local" !== h && "function" != typeof h || (U._fullCalendar_weekCalc = h),
            aa && f(aa)
        }),
        T.defaultAllDayEventDuration = b.duration(T.options.defaultAllDayEventDuration),
        T.defaultTimedEventDuration  = b.duration(T.options.defaultTimedEventDuration),
        T.moment                     = function () {
            var a;
            return "local" === T.options.timezone
                ? (a = Wa.moment.apply(null, arguments), a.hasTime() && a.local())
                : a = "UTC" === T.options.timezone
                    ? Wa
                        .moment
                        .utc
                        .apply(null, arguments)
                    : Wa
                        .moment
                        .parseZone
                        .apply(null, arguments),
            f(a),
            a
        },
        T.getIsAmbigTimezone         = function () {
            return "local" !== T.options.timezone && "UTC" !== T.options.timezone
        },
        T.applyTimezone              = function (a) {
            if (!a.hasTime()) 
                return a.clone();
            var b,
                c = T.moment(a.toArray()),
                d = a.time() - c.time();
            return d && (b = c.clone().add(d), a.time() - b.time() === 0 && (c = b)),
            c
        },
        T.getNow                     = function () {
            var a = T.options.now;
            return "function" == typeof a && (a = a()),
            T
                .moment(a)
                .stripZone()
        },
        T.getEventEnd                = function (a) {
            return a.end
                ? a
                    .end
                    .clone()
                : T.getDefaultEventEnd(a.allDay, a.start)
        },
        T.getDefaultEventEnd         = function (a, b) {
            var c = b.clone();
            return a
                ? c
                    .stripTime()
                    .add(T.defaultAllDayEventDuration)
                : c.add(T.defaultTimedEventDuration),
            T.getIsAmbigTimezone() && c.stripZone(),
            c
        },
        T.humanizeDuration           = function (a) {
            return (a.locale || a.lang)
                .call(a, T.options.lang)
                .humanize()
        },
        Ua.call(T);
        var V,
            W,
            Y,
            Z,
            $,
            _,
            aa,
            ba = T.isFetchNeeded,
            ca = T.fetchEvents,
            da = T.fetchEventSources,
            ea = c[0],
            fa = {},
            ga = 0,
            ha = [];
        aa                       = null != T.options.defaultDate
            ? T
                .moment(T.options.defaultDate)
                .stripZone()
            : T.getNow(),
        T.getSuggestedViewHeight = function () {
            return void 0 === $ && p(),
            $
        },
        T.isHeightAuto           = function () {
            return "auto" === T.options.contentHeight || "auto" === T.options.height
        },
        T.freezeContentHeight    = M,
        T.unfreezeContentHeight  = N,
        T.initialize()
    }
    function Ra(b) {
        a
            .each(Db, function (a, c) {
                null == b[a] && (b[a] = c(b))
            })
    }
    function Sa(a) {
        var c = b.localeData || b.langData;
        return c.call(b, a) || c.call(b, "en")
    }
    function Ta(b) {
        function c() {
            var c = b.options,
                f = c.header;
            n = c.theme
                ? "ui"
                : "fc",
            f
                ? (m
                    ? m.empty()
                    : m = this.el = a("<div class='fc-toolbar'/>"), m.append(e("left")).append(e("right")).append(e("center")).append('<div class="fc-clear"/>'))
                : d()
        }
        function d() {
            m && (m.remove(), m = l.el = null)
        }
        function e(c) {
            var d = a('<div class="fc-' + c + '"/>'),
                e = b.options,
                f = e.header[c];
            return f && a.each(f.split(" "), function (c) {
                var f,
                    g = a(),
                    h = !0;
                a.each(this.split(","), function (c, d) {
                    var f,
                        i,
                        j,
                        k,
                        l,
                        m,
                        p,
                        q,
                        r,
                        s;
                    "title" == d
                        ? (g = g.add(a("<h2>&nbsp;</h2>")), h = !1)
                        : ((f = (e.customButtons || {})[d])
                            ? (j = function (a) {
                                f.click && f
                                    .click
                                    .call(s[0], a)
                            },
                            k    = "",
                            l    = f.text)
                            : (i = b.getViewSpec(d))
                                ? (j = function () {
                                    b.changeView(d)
                                },
                                o.push(d),
                                k = i.buttonTextOverride,
                                l = i.buttonTextDefault)
                                : b[d] && (j = function () {
                                    b[d]()
                                },
                                k  = (b.overrides.buttonText || {})[d],
                                l  = e.buttonText[d]), j && (m = f
                            ? f.themeIcon
                            : e.themeButtonIcons[d], p = f
                            ? f.icon
                            : e.buttonIcons[d], q = k
                            ? ca(k)
                            : m && e.theme
                                ? "<span class='ui-icon ui-icon-" + m + "'></span>"
                                : p && !e.theme
                                    ? "<span class='fc-icon fc-icon-" + p + "'></span>"
                                    : ca(l), r = [
                            "fc-" + d + "-button",
                            n + "-button",
                            n + "-state-default"
                        ], s = a('<button type="button" class="' + r.join(" ") + '">' + q + "</button>").click(function (a) {
                            s.hasClass(n + "-state-disabled") || (j(a), (s.hasClass(n + "-state-active") || s.hasClass(n + "-state-disabled")) && s.removeClass(n + "-state-hover"))
                        }).mousedown(function () {
                            s
                                .not("." + n + "-state-active")
                                .not("." + n + "-state-disabled")
                                .addClass(n + "-state-down")
                        }).mouseup(function () {
                            s.removeClass(n + "-state-down")
                        }).hover(function () {
                            s
                                .not("." + n + "-state-active")
                                .not("." + n + "-state-disabled")
                                .addClass(n + "-state-hover")
                        }, function () {
                            s
                                .removeClass(n + "-state-hover")
                                .removeClass(n + "-state-down")
                        }), g = g.add(s)))
                }),
                h && g
                    .first()
                    .addClass(n + "-corner-left")
                    .end()
                    .last()
                    .addClass(n + "-corner-right")
                    .end(),
                g.length > 1
                    ? (f = a("<div/>"), h && f.addClass("fc-button-group"), f.append(g), d.append(f))
                    : d.append(g)
            }),
            d
        }
        function f(a) {
            m && m
                .find("h2")
                .text(a)
        }
        function g(a) {
            m && m
                .find(".fc-" + a + "-button")
                .addClass(n + "-state-active")
        }
        function h(a) {
            m && m
                .find(".fc-" + a + "-button")
                .removeClass(n + "-state-active")
        }
        function i(a) {
            m && m
                .find(".fc-" + a + "-button")
                .prop("disabled", !0)
                .addClass(n + "-state-disabled")
        }
        function j(a) {
            m && m
                .find(".fc-" + a + "-button")
                .prop("disabled", !1)
                .removeClass(n + "-state-disabled")
        }
        function k() {
            return o
        }
        var l = this;
        l.render              = c,
        l.removeElement       = d,
        l.updateTitle         = f,
        l.activateButton      = g,
        l.deactivateButton    = h,
        l.disableButton       = i,
        l.enableButton        = j,
        l.getViewsWithButtons = k,
        l.el                  = null;
        var m,
            n,
            o = []
    }
    function Ua() {
        function c(a, b) {
            return !W || W > a || b > X
        }
        function d(a, b) {
            W = a,
            X = b,
            e($, "reset")
        }
        function e(a, b) {
            var c,
                d;
            for ("reset" === b
                ? da = []
                : "add" !== b && (da = u(da, a)), c = 0; c < a.length; c++) 
                d = a[c],
                "pending" !== d._status && ca++,
                d._fetchId = (d._fetchId || 0) + 1,
                d._status = "pending";
            for (c = 0; c < a.length; c++) 
                d = a[c],
                f(d, d._fetchId)
        }
        function f(b, c) {
            i(b, function (d) {
                var e,
                    f,
                    g,
                    i = a.isArray(b.events);
                if (c === b._fetchId && "rejected" !== b._status) {
                    if (b._status = "resolved", d) 
                        for (e = 0; e < d.length; e++) 
                            f = d[e],
                            g = i
                                ? f
                                : C(f, b),
                            g && da.push.apply(da, G(g));
                h()
                }
            })
        }
        function g(a) {
            var b = "pending" === a._status;
            a._status = "rejected",
            b && h()
        }
        function h() {
            ca--,
            ca || Y(da)
        }
        function i(b, c) {
            var d,
                e,
                f = Wa.sourceFetchers;
            for (d = 0; d < f.length; d++) {
                if (e = f[d].call(U, b, W.clone(), X.clone(), U.options.timezone, c), e === !0) 
                    return;
                if ("object" == typeof e) 
                    return void i(e, c)
            }
            var g = b.events;
            if (g) 
                a.isFunction(g)
                    ? (U.pushLoading(), g.call(U, W.clone(), X.clone(), U.options.timezone, function (a) {
                        c(a),
                        U.popLoading()
                    }))
                    : a.isArray(g)
                        ? c(g)
                        : c();
            else {
                var h = b.url;
                if (h) {
                    var j,
                        k = b.success,
                        l = b.error,
                        m = b.complete;
                    j = a.isFunction(b.data)
                        ? b.data()
                        : b.data;
                    var n = a.extend({}, j || {}),
                        o = ba(b.startParam, U.options.startParam),
                        p = ba(b.endParam, U.options.endParam),
                        q = ba(b.timezoneParam, U.options.timezoneParam);
                    o && (n[o] = W.format()),
                    p && (n[p] = X.format()),
                    U.options.timezone && "local" != U.options.timezone && (n[q] = U.options.timezone),
                    U.pushLoading(),
                    a.ajax(a.extend({}, Eb, b, {
                        complete: function () {
                            aa(m, this, arguments),
                            U.popLoading()
                        },
                        data    : n,
                        error   : function () {
                            aa(l, this, arguments),
                            c()
                        },
                        success : function (b) {
                            b = b || [];
                            var d = aa(k, this, arguments);
                            a.isArray(d) && (b = d),
                            c(b)
                        }
                    }))
                } else 
                    c()
            }
        }
        function j(a) {
            var b = k(a);
            b && ($.push(b), e([b], "add"))
        }
        function k(b) {
            var c,
                d,
                e = Wa.sourceNormalizers;
            if (a.isFunction(b) || a.isArray(b)
                ? c = {
                    events: b
                }
                : "string" == typeof b
                    ? c = {
                        url: b
                    }
                    : "object" == typeof b && (c = a.extend({}, b)), c) {
                for (c.className
                    ? "string" == typeof c.className && (c.className = c.className.split(/\s+/))
                    : c.className = [], a.isArray(c.events) && (c.origArray = c.events, c.events = a.map(c.events, function (a) {
                    return C(a, c)
                })), d = 0; d < e.length; d++) 
                    e[d].call(U, c);
                return c
            }
        }
        function l(a) {
            n(r(a))
        }
        function m(a) {
            null == a
                ? n($, !0)
                : n(q(a))
        }
        function n(b, c) {
            var d;
            for (d = 0; d < b.length; d++) 
                g(b[d]);
            c
                ? ($ = [], da = [])
                : ($ = a.grep($, function (a) {
                    for (d = 0; d < b.length; d++) 
                        if (a === b[d]) 
                            return !1;
                return !0
                }), da = u(da, b)),
            Y(da)
        }
        function o() {
            return $.slice(1)
        }
        function p(b) {
            return a.grep($, function (a) {
                return a.id && a.id === b
            })[0]
        }
        function q(b) {
            b
                ? a.isArray(b) || (b = [b])
                : b = [];
            var c,
                d = [];
            for (c = 0; c < b.length; c++) 
                d.push.apply(d, r(b[c]));
            return d
        }
        function r(b) {
            var c,
                d;
            for (c = 0; c < $.length; c++) 
                if (d = $[c], d === b) 
                    return [d];
        return d = p(b),
            d
                ? [d]
                : a.grep($, function (a) {
                    return s(b, a)
                })
        }
        function s(a, b) {
            return a && b && t(a) == t(b)
        }
        function t(a) {
            return ("object" == typeof a
                ? a.origArray || a.googleCalendarId || a.url || a.events
                : null) || a
        }
        function u(b, c) {
            return a.grep(b, function (a) {
                for (var b = 0; b < c.length; b++) 
                    if (a.source === c[b]) 
                        return !1;
            return !0
            })
        }
        function v(a) {
            a.start     = U.moment(a.start),
            a.end
                ? a.end = U.moment(a.end)
                : a.end = null,
            H(a, w(a)),
            Y(da)
        }
        function w(b) {
            var c = {};
            return a.each(b, function (a, b) {
                x(a) && void 0 !== b && _(b) && (c[a] = b)
            }),
            c
        }
        function x(a) {
            return !/^_|^(id|allDay|start|end)$/.test(a)
        }
        function y(a, b) {
            var c,
                d,
                e,
                f = C(a);
            if (f) {
                for (c = G(f), d = 0; d < c.length; d++) 
                    e = c[d],
                    e.source || (b && (Z.events.push(e), e.source = Z), da.push(e));
                return Y(da),
                c
            }
            return []
        }
        function z(b) {
            var c,
                d;
            for (null == b
                ? b = function () {
                    return !0
                }
                : a.isFunction(b) || (c = b + "", b = function (a) {
                    return a._id == c
                }),
            da = a.grep(da, b, !0),
            d = 0; d < $.length; d++) 
                a.isArray($[d].events) && ($[d].events = a.grep($[d].events, b, !0));
            Y(da)
        }
        function A(b) {
            return a.isFunction(b)
                ? a.grep(da, b)
                : null != b
                    ? (b += "", a.grep(da, function (a) {
                        return a._id == b
                    }))
                    : da
        }
        function B(a) {
            a.start = U.moment(a.start),
            a.end && (a.end = U.moment(a.end)),
            Va(a)
        }
        function C(c, d) {
            var e,
                f,
                g,
                h = {};
            if (U.options.eventDataTransform && (c = U.options.eventDataTransform(c)), d && d.eventDataTransform && (c = d.eventDataTransform(c)), a.extend(h, c), d && (h.source = d), h._id = c._id || (void 0 === c.id
                ? "_fc" + Fb++
                : c.id + ""), c.className
                ? "string" == typeof c.className
                    ? h.className = c.className.split(/\s+/)
                    : h.className = c.className
                : h.className = [], e = c.start || c.date, f = c.end, V(e) && (e = b.duration(e)), V(f) && (f = b.duration(f)), c.dow || b.isDuration(e) || b.isDuration(f)) 
                h.start      = e
                    ? b.duration(e)
                    : null,
                h.end        = f
                    ? b.duration(f)
                    : null,
                h._recurring = !0;
            else {
                if (e && (e = U.moment(e), !e.isValid())) 
                    return !1;
                f && (f = U.moment(f), f.isValid() || (f = null)),
                g = c.allDay,
                void 0 === g && (g = ba(d
                    ? d.allDayDefault
                    : void 0, U.options.allDayDefault)),
                D(e, f, g, h)
            }
            return U.normalizeEvent(h),
            h
        }
        function D(a, b, c, d) {
            d.start  = a,
            d.end    = b,
            d.allDay = c,
            E(d),
            Va(d)
        }
        function E(a) {
            F(a),
            a.end && !a
                .end
                .isAfter(a.start) && (a.end = null),
            a.end || (U.options.forceEventDuration
                ? a.end = U.getDefaultEventEnd(a.allDay, a.start)
                : a.end = null)
        }
        function F(a) {
            null == a.allDay && (a.allDay = !(a.start.hasTime() || a.end && a.end.hasTime())),
            a.allDay
                ? (a.start.stripTime(), a.end && a.end.stripTime())
                : (a.start.hasTime() || (a.start = U.applyTimezone(a.start.time(0))), a.end && !a.end.hasTime() && (a.end = U.applyTimezone(a.end.time(0))))
        }
        function G(b, c, d) {
            var e,
                f,
                g,
                h,
                i,
                j,
                k,
                l,
                m,
                n = [];
            if (c = c || W, d = d || X, b) 
                if (b._recurring) {
                    if (f = b.dow) 
                        for (e = {}, g = 0; g < f.length; g++) 
                            e[f[g]] = !0;
                for (h = c.clone().stripTime(); h.isBefore(d);) 
                        e && !e[h.day()] || (i = b.start, j = b.end, k = h.clone(), l = null, i && (k = k.time(i)), j && (l = h.clone().time(j)), m = a.extend({}, b), D(k, l, !i && !j, m), n.push(m)),
                        h.add(1, "days")
                } else 
                    n.push(b);
        return n
        }
        function H(b, c, d) {
            function e(a, b) {
                return d
                    ? N(a, b, d)
                    : c.allDay
                        ? M(a, b)
                        : L(a, b)
            }
            var f,
                g,
                h,
                i,
                j,
                k,
                l = {};
            return c = c || {},
            c.start || (c.start = b.start.clone()),
            void 0 === c.end && (c.end = b.end
                ? b.end.clone()
                : null),
            null == c.allDay && (c.allDay = b.allDay),
            E(c),
            f        = {
                allDay: c.allDay,
                end   : b._end
                    ? b
                        ._end
                        .clone()
                    : U.getDefaultEventEnd(b._allDay, b._start),
                start : b
                    ._start
                    .clone()
            },
            E(f),
            g       = null !== b._end && null === c.end,
            h       = e(c.start, f.start),
            c.end
                ? (i = e(c.end, f.end), j = i.subtract(h))
                : j = null,
            a.each(c, function (a, b) {
                x(a) && void 0 !== b && (l[a] = b)
            }),
            k = I(A(b._id), g, c.allDay, h, j, l), {
                dateDelta    : h,
                durationDelta: j,
                undo         : k
            }
        }
        function I(b, c, d, e, f, g) {
            var h = U.getIsAmbigTimezone(),
                i = [];
            return e && !e.valueOf() && (e = null),
            f && !f.valueOf() && (f = null),
            a.each(b, function (b, j) {
                var k,
                    l;
                k = {
                    allDay: j.allDay,
                    end   : j.end
                        ? j
                            .end
                            .clone()
                        : null,
                    start : j
                        .start
                        .clone()
                },
                a.each(g, function (a) {
                    k[a] = j[a]
                }),
                l = {
                    allDay: d,
                    end   : j._end,
                    start : j._start
                },
                E(l),
                c
                    ? l.end = null
                    : f && !l.end && (l.end = U.getDefaultEventEnd(l.allDay, l.start)),
                e && (l.start.add(e), l.end && l.end.add(e)),
                f && l
                    .end
                    .add(f),
                h && !l.allDay && (e || f) && (l.start.stripZone(), l.end && l.end.stripZone()),
                a.extend(j, g, l),
                Va(j),
                i.push(function () {
                    a.extend(j, k),
                    Va(j)
                })
            }),
            function () {
                for (var a = 0; a < i.length; a++) 
                    i[a]()
            }
        }
        function J(b) {
            var c,
                d = U.options.businessHours,
                e = {
                    className: "fc-nonbusiness",
                    dow      : [
                        1, 2, 3, 4, 5
                    ],
                    end      : "17:00",
                    rendering: "inverse-background",
                    start    : "09:00"
                },
                f = U.getView();
            return d && (c = a.extend({}, e, "object" == typeof d
                ? d
                : {})),
            c
                ? (b && (c.start = null, c.end = null), G(C(c), f.start, f.end))
                : []
        }
        function K(a, b) {
            var c = b.source || {},
                d = ba(b.constraint, c.constraint, U.options.eventConstraint),
                e = ba(b.overlap, c.overlap, U.options.eventOverlap);
            return Q(a, d, e, b)
        }
        function O(b, c, d) {
            var e,
                f;
            return d && (e = a.extend({}, d, c), f = G(C(e))[0]),
            f
                ? K(b, f)
                : P(b)
        }
        function P(a) {
            return Q(a, U.options.selectConstraint, U.options.selectOverlap)
        }
        function Q(a, b, c, d) {
            var e,
                f,
                g,
                h,
                i,
                j;
            if (null != b) {
                for (e = R(b), f = !1, h = 0; h < e.length; h++) 
                    if (S(e[h], a)) {
                        f = !0;
                        break
                    }
                if (!f) 
                    return !1
            }
            for (g = U.getPeerEvents(a, d), h = 0; h < g.length; h++) 
                if (i = g[h], T(i, a)) {
                    if (c === !1) 
                        return !1;
                    if ("function" == typeof c && !c(i, d)) 
                        return !1;
                    if (d) {
                        if (j = ba(i.overlap, (i.source || {}).overlap), j === !1) 
                            return !1;
                        if ("function" == typeof j && !j(d, i)) 
                            return !1
                    }
                }
            return !0
        }
        function R(a) {
            return "businessHours" === a
                ? J()
                : "object" == typeof a
                    ? G(C(a))
                    : A(a)
        }
        function S(a, b) {
            var c = a
                    .start
                    .clone()
                    .stripZone(),
                d = U
                    .getEventEnd(a)
                    .stripZone();
            return b.start >= c && b.end <= d
        }
        function T(a, b) {
            var c = a
                    .start
                    .clone()
                    .stripZone(),
                d = U
                    .getEventEnd(a)
                    .stripZone();
            return b.start < d && b.end > c
        }
        var U = this;
        U.isFetchNeeded               = c,
        U.fetchEvents                 = d,
        U.fetchEventSources           = e,
        U.getEventSources             = o,
        U.getEventSourceById          = p,
        U.getEventSourcesByMatchArray = q,
        U.getEventSourcesByMatch      = r,
        U.addEventSource              = j,
        U.removeEventSource           = l,
        U.removeEventSources          = m,
        U.updateEvent                 = v,
        U.renderEvent                 = y,
        U.removeEvents                = z,
        U.clientEvents                = A,
        U.mutateEvent                 = H,
        U.normalizeEventDates         = E,
        U.normalizeEventTimes         = F;
        var W,
            X,
            Y  = U.reportEvents,
            Z  = {
                events: []
            },
            $  = [Z],
            ca = 0,
            da = [];
        a.each((U.options.events
            ? [U.options.events]
            : []).concat(U.options.eventSources || []), function (a, b) {
            var c = k(b);
            c && $.push(c)
        }),
        U.rezoneArrayEventSources = function () {
            var b,
                c,
                d;
            for (b = 0; b < $.length; b++) 
                if (c = $[b].events, a.isArray(c)) 
                    for (d = 0; d < c.length; d++) 
                        B(c[d])
        },
        U.getBusinessHoursEvents  = J,
        U.isEventSpanAllowed      = K,
        U.isExternalSpanAllowed   = O,
        U.isSelectionSpanAllowed  = P,
        U.getEventCache           = function () {
            return da
        }
    }
    function Va(a) {
        a._allDay = a.allDay,
        a._start  = a
            .start
            .clone(),
        a._end    = a.end
            ? a
                .end
                .clone()
            : null
    }
    var Wa = a.fullCalendar = {
            internalApiVersion: 4,
            version           : "2.9.0"
        },
        Xa = Wa.views = {};
    a.fn.fullCalendar = function (b) {
        var c = Array
                .prototype
                .slice
                .call(arguments, 1),
            d = this;
        return this.each(function (e, f) {
            var g,
                h = a(f),
                i = h.data("fullCalendar");
            "string" == typeof b
                ? i && a.isFunction(i[b]) && (g = i[b].apply(i, c), e || (d = g), "destroy" === b && h.removeData("fullCalendar"))
                : i || (i = new zb(h, b), h.data("fullCalendar", i), i.render())
        }),
        d
    };
    var Ya = ["header", "buttonText", "buttonIcons", "themeButtonIcons"];
    Wa.intersectRanges       = K,
    Wa.applyAll              = aa,
    Wa.debounce              = ja,
    Wa.isInt                 = ha,
    Wa.htmlEscape            = ca,
    Wa.cssToStr              = ea,
    Wa.proxy                 = ia,
    Wa.capitaliseFirstLetter = fa,
    Wa.getOuterRect          = n,
    Wa.getClientRect         = o,
    Wa.getContentRect        = p,
    Wa.getScrollbarWidths    = q;
    var Za = null;
    Wa.preventDefault           = z,
    Wa.intersectRects           = C,
    Wa.parseFieldSpecs          = G,
    Wa.compareByFieldSpecs      = H,
    Wa.compareByFieldSpec       = I,
    Wa.flexibleCompare          = J,
    Wa.computeIntervalUnit      = O,
    Wa.divideRangeByDuration    = Q,
    Wa.divideDurationByDuration = R,
    Wa.multiplyDuration         = S,
    Wa.durationHasTime          = T;
    var $a = [
            "sun",
            "mon",
            "tue",
            "wed",
            "thu",
            "fri",
            "sat"
        ],
        _a = [
            "year",
            "month",
            "week",
            "day",
            "hour",
            "minute",
            "second",
            "millisecond"
        ];
    Wa.log  = function () {
        var a = window.console;
        return a && a.log
            ? a
                .log
                .apply(a, arguments)
            : void 0
    },
    Wa.warn = function () {
        var a = window.console;
        return a && a.warn
            ? a
                .warn
                .apply(a, arguments)
            : Wa
                .log
                .apply(Wa, arguments)
    };
    var ab,
        bb,
        cb,
        db = {}.hasOwnProperty,
        eb = /^\s*\d{4}-\d\d$/,
        fb = /^\s*\d{4}-(?:(\d\d-\d\d)|(W\d\d$)|(W\d\d-\d)|(\d\d\d))((T| )(\d\d(:\d\d(:\d\d(\.\d+)?)?)?)?)?$/,
        gb = b.fn,
        hb = a.extend({}, gb);
    Wa.moment           = function () {
        return la(arguments)
    },
    Wa.moment.utc       = function () {
        var a = la(arguments, !0);
        return a.hasTime() && a.utc(),
        a
    },
    Wa.moment.parseZone = function () {
        return la(arguments, !0, !0)
    },
    gb.clone            = function () {
        var a = hb
            .clone
            .apply(this, arguments);
        return na(this, a),
        this._fullCalendar && (a._fullCalendar = !0),
        a
    },
    gb.week             = gb.weeks = function (a) {
        var b = (this._locale || this._lang)._fullCalendar_weekCalc;
        return null == a && "function" == typeof b
            ? b(this)
            : "ISO" === b
                ? hb
                    .isoWeek
                    .apply(this, arguments)
                : hb
                    .week
                    .apply(this, arguments)
    },
    gb.time             = function (a) {
        if (!this._fullCalendar) 
            return hb.time.apply(this, arguments);
        if (null == a) 
            return b.duration({
                hours       : this.hours(),
                milliseconds: this.milliseconds(),
                minutes     : this.minutes(),
                seconds     : this.seconds()
            });
        this._ambigTime = !1,
        b.isDuration(a) || b.isMoment(a) || (a = b.duration(a));
        var c = 0;
        return b.isDuration(a) && (c = 24 * Math.floor(a.asDays())),
        this
            .hours(c + a.hours())
            .minutes(a.minutes())
            .seconds(a.seconds())
            .milliseconds(a.milliseconds())
    },
    gb.stripTime        = function () {
        var a;
        return this._ambigTime || (a = this.toArray(), this.utc(), bb(this, a.slice(0, 3)), this._ambigTime = !0, this._ambigZone = !0),
        this
    },
    gb.hasTime          = function () {
        return !this._ambigTime
    },
    gb.stripZone        = function () {
        var a,
            b;
        return this._ambigZone || (a = this.toArray(), b = this._ambigTime, this.utc(), bb(this, a), this._ambigTime = b || !1, this._ambigZone = !0),
        this
    },
    gb.hasZone          = function () {
        return !this._ambigZone
    },
    gb.local            = function () {
        var a = this.toArray(),
            b = this._ambigZone;
        return hb
            .local
            .apply(this, arguments),
        this._ambigTime = !1,
        this._ambigZone = !1,
        b && cb(this, a),
        this
    },
    gb.utc              = function () {
        return hb
            .utc
            .apply(this, arguments),
        this._ambigTime = !1,
        this._ambigZone = !1,
        this
    },
    a.each([
        "zone", "utcOffset"
    ], function (a, b) {
        hb[b] && (gb[b] = function (a) {
            return null != a && (this._ambigTime = !1, this._ambigZone = !1),
            hb[b].apply(this, arguments)
        })
    }),
    gb.format      = function () {
        return this._fullCalendar && arguments[0]
            ? qa(this, arguments[0])
            : this._ambigTime
                ? pa(this, "YYYY-MM-DD")
                : this._ambigZone
                    ? pa(this, "YYYY-MM-DD[T]HH:mm:ss")
                    : hb
                        .format
                        .apply(this, arguments);
    },
    gb.toISOString = function () {
        return this._ambigTime
            ? pa(this, "YYYY-MM-DD")
            : this._ambigZone
                ? pa(this, "YYYY-MM-DD[T]HH:mm:ss")
                : hb
                    .toISOString
                    .apply(this, arguments)
    },
    gb.isWithin    = function (a, b) {
        var c = ma([this, a, b]);
        return c[0] >= c[1] && c[0] < c[2]
    },
    gb.isSame      = function (a, b) {
        var c;
        return this._fullCalendar
            ? b
                ? (c = ma([
                    this, a
                ], !0), hb.isSame.call(c[0], c[1], b))
                : (a = Wa.moment.parseZone(a), hb.isSame.call(this, a) && Boolean(this._ambigTime) === Boolean(a._ambigTime) && Boolean(this._ambigZone) === Boolean(a._ambigZone))
            : hb
                .isSame
                .apply(this, arguments)
    },
    a.each([
        "isBefore", "isAfter"
    ], function (a, b) {
        gb[b] = function (a, c) {
            var d;
            return this._fullCalendar
                ? (d = ma([this, a]), hb[b].call(d[0], d[1], c))
                : hb[b].apply(this, arguments)
        }
    }),
    ab = "_d" in b() && "updateOffset" in b,
    bb = ab
        ? function (a, c) {
            a
                ._d
                .setTime(Date.UTC.apply(Date, c)),
            b.updateOffset(a, !1)
        }
        : oa,
    cb = ab
        ? function (a, c) {
            a
                ._d
                .setTime(+ new Date(c[0] || 0, c[1] || 0, c[2] || 0, c[3] || 0, c[4] || 0, c[5] || 0, c[6] || 0)),
            b.updateOffset(a, !1)
        }
        : oa;
    var ib = {
        T: function (a) {
            return pa(a, "A").charAt(0)
        },
        t: function (a) {
            return pa(a, "a").charAt(0)
        }
    };
    Wa.formatRange = ta;
    var jb = {
            a: "second",
            A: "second",
            D: "day",
            d: "day",
            h: "second",
            H: "second",
            M: "month",
            m: "second",
            s: "second",
            T: "second",
            t: "second",
            Y: "year"
        },
        kb = {};
    Wa.Class  = ya,
    ya.extend = function () {
        var a,
            b,
            c = arguments.length;
        for (a = 0; c > a; a++) 
            b = arguments[a],
            c - 1 > a && Aa(this, b);
        return za(this, b || {})
    },
    ya.mixin  = function (a) {
        Aa(this, a)
    };
    var lb = Wa.EmitterMixin = {
            off        : function (b, c) {
                return a(this).off(b, c),
                this
            },
            on         : function (b, c) {
                var d = function (a, b) {
                    return c.apply(b.context || this, b.args || [])
                };
                return c.guid || (c.guid = a.guid++),
                d.guid = c.guid,
                a(this).on(b, d),
                this
            },
            trigger    : function (b) {
                var c = Array
                    .prototype
                    .slice
                    .call(arguments, 1);
                return a(this).triggerHandler(b, {args: c}),
                this
            },
            triggerWith: function (b, c, d) {
                return a(this).triggerHandler(b, {
                    args   : d,
                    context: c
                }),
                this
            }
        },
        mb = Wa.ListenerMixin = function () {
            var b = 0,
                c = {
                    getListenerNamespace: function () {
                        return null == this.listenerId && (this.listenerId = b++),
                        "_listener" + this.listenerId
                    },
                    listenerId          : null,
                    listenTo            : function (b, c, d) {
                        if ("object" == typeof c) 
                            for (var e in c) 
                                c.hasOwnProperty(e) && this.listenTo(b, e, c[e]);
                    else 
                            "string" == typeof c && b.on(c + "." + this.getListenerNamespace(), a.proxy(d, this))
                    },
                    stopListeningTo     : function (a, b) {
                        a.off((b || "") + "." + this.getListenerNamespace())
                    }
                };
            return c
        }(),
        nb = {
            delayUnignoreMouse: null,
            initMouseIgnoring : function (a) {
                this.delayUnignoreMouse = ja(ia(this, "unignoreMouse"), a || 1e3)
            },
            isIgnoringMouse   : !1,
            tempIgnoreMouse   : function () {
                this.isIgnoringMouse = !0,
                this.delayUnignoreMouse()
            },
            unignoreMouse     : function () {
                this.isIgnoringMouse = !1
            }
        },
        ob = ya.extend(mb, {
            constructor      : function (a) {
                this.options = a || {}
            },
            documentMousedown: function (b) {
                this.el && !a(b.target)
                    .closest(this.el)
                    .length && this.hide()
            },
            el               : null,
            hide             : function () {
                this.isHidden || (this.el.hide(), this.isHidden = !0, this.trigger("hide"))
            },
            isHidden         : !0,
            margin           : 10,
            options          : null,
            position         : function () {
                var b,
                    c,
                    d,
                    e,
                    f,
                    g = this.options,
                    h = this
                        .el
                        .offsetParent()
                        .offset(),
                    i = this
                        .el
                        .outerWidth(),
                    j = this
                        .el
                        .outerHeight(),
                    k = a(window),
                    l = m(this.el);
                e = g.top || 0,
                f = void 0 !== g.left
                    ? g.left
                    : void 0 !== g.right
                        ? g.right - i
                        : 0,
                l.is(window) || l.is(document)
                    ? (l = k, b = 0, c = 0)
                    : (d = l.offset(), b = d.top, c = d.left),
                b += k.scrollTop(),
                c += k.scrollLeft(),
                g.viewportConstrain !== !1 && (e = Math.min(e, b + l.outerHeight() - j - this.margin), e = Math.max(e, b + this.margin), f = Math.min(f, c + l.outerWidth() - i - this.margin), f = Math.max(f, c + this.margin)),
                this
                    .el
                    .css({
                        left: f - h.left,
                        top : e - h.top
                    })
            },
            removeElement    : function () {
                this.hide(),
                this.el && (this.el.remove(), this.el = null),
                this.stopListeningTo(a(document), "mousedown")
            },
            render           : function () {
                var b = this,
                    c = this.options;
                this.el = a('<div class="fc-popover"/>')
                    .addClass(c.className || "")
                    .css({left: 0, top: 0})
                    .append(c.content)
                    .appendTo(c.parentEl),
                this
                    .el
                    .on("click", ".fc-close", function () {
                        b.hide()
                    }),
                c.autoHide && this.listenTo(a(document), "mousedown", this.documentMousedown)
            },
            show             : function () {
                this.isHidden && (this.el || this.render(), this.el.show(), this.position(), this.isHidden = !1, this.trigger("show"))
            },
            trigger          : function (a) {
                this.options[a] && this
                    .options[a]
                    .apply(this, Array.prototype.slice.call(arguments, 1))
            }
        }),
        pb = Wa.CoordCache = ya.extend({
            bottoms             : null,
            boundingRect        : null,
            build               : function () {
                var a = this.forcedOffsetParentEl || this
                    .els
                    .eq(0)
                    .offsetParent();
                this.origin       = a.offset(),
                this.boundingRect = this.queryBoundingRect(),
                this.isHorizontal && this.buildElHorizontals(),
                this.isVertical && this.buildElVerticals()
            },
            buildElHorizontals  : function () {
                var b = [],
                    c = [];
                this
                    .els
                    .each(function (d, e) {
                        var f = a(e),
                            g = f
                                .offset()
                                .left,
                            h = f.outerWidth();
                        b.push(g),
                        c.push(g + h)
                    }),
                this.lefts  = b,
                this.rights = c
            },
            buildElVerticals    : function () {
                var b = [],
                    c = [];
                this
                    .els
                    .each(function (d, e) {
                        var f = a(e),
                            g = f
                                .offset()
                                .top,
                            h = f.outerHeight();
                        b.push(g),
                        c.push(g + h)
                    }),
                this.tops    = b,
                this.bottoms = c
            },
            clear               : function () {
                this.origin       = null,
                this.boundingRect = null,
                this.lefts        = null,
                this.rights       = null,
                this.tops         = null,
                this.bottoms      = null
            },
            constructor         : function (b) {
                this.els                  = a(b.els),
                this.isHorizontal         = b.isHorizontal,
                this.isVertical           = b.isVertical,
                this.forcedOffsetParentEl = b.offsetParent
                    ? a(b.offsetParent)
                    : null
            },
            els                 : null,
            ensureBuilt         : function () {
                this.origin || this.build()
            },
            forcedOffsetParentEl: null,
            getBottomOffset     : function (a) {
                return this.ensureBuilt(),
                this.bottoms[a]
            },
            getBottomPosition   : function (a) {
                return this.ensureBuilt(),
                this.bottoms[a] - this.origin.top
            },
            getHeight           : function (a) {
                return this.ensureBuilt(),
                this.bottoms[a] - this.tops[a]
            },
            getHorizontalIndex  : function (a) {
                this.ensureBuilt();
                var b,
                    c = this.boundingRect,
                    d = this.lefts,
                    e = this.rights,
                    f = d.length;
                if (!c || a >= c.left && a < c.right) 
                    for (b = 0; f > b; b++) 
                        if (a >= d[b] && a < e[b]) 
                            return b
            },
            getLeftOffset       : function (a) {
                return this.ensureBuilt(),
                this.lefts[a]
            },
            getLeftPosition     : function (a) {
                return this.ensureBuilt(),
                this.lefts[a] - this.origin.left
            },
            getRightOffset      : function (a) {
                return this.ensureBuilt(),
                this.rights[a]
            },
            getRightPosition    : function (a) {
                return this.ensureBuilt(),
                this.rights[a] - this.origin.left
            },
            getTopOffset        : function (a) {
                return this.ensureBuilt(),
                this.tops[a]
            },
            getTopPosition      : function (a) {
                return this.ensureBuilt(),
                this.tops[a] - this.origin.top
            },
            getVerticalIndex    : function (a) {
                this.ensureBuilt();
                var b,
                    c = this.boundingRect,
                    d = this.tops,
                    e = this.bottoms,
                    f = d.length;
                if (!c || a >= c.top && a < c.bottom) 
                    for (b = 0; f > b; b++) 
                        if (a >= d[b] && a < e[b]) 
                            return b
            },
            getWidth            : function (a) {
                return this.ensureBuilt(),
                this.rights[a] - this.lefts[a]
            },
            isHorizontal        : !1,
            isVertical          : !1,
            lefts               : null,
            origin              : null,
            queryBoundingRect   : function () {
                var a = m(this.els.eq(0));
                return a.is(document)
                    ? void 0
                    : o(a)
            },
            rights              : null,
            tops                : null
        }),
        qb = Wa.DragListener = ya.extend(mb, nb, {
            bindHandlers           : function () {
                var b = this,
                    c = 1;
                this.isTouch
                    ? (this.listenTo(a(document), {
                        touchcancel: this.endInteraction,
                        touchend   : this.endInteraction,
                        touchmove  : this.handleTouchMove,
                        touchstart : function (a) {
                            c
                                ? c--
                                : b.endInteraction(a, !0)
                        }
                    }), !A(this.handleTouchScrollProxy) && this.scrollEl && this.listenTo(this.scrollEl, "scroll", this.handleTouchScroll))
                    : this.listenTo(a(document), {
                        mousemove: this.handleMouseMove,
                        mouseup  : this.endInteraction
                    }),
                this.listenTo(a(document), {
                    contextmenu: z,
                    selectstart: z
                })
            },
            constructor            : function (a) {
                this.options                = a || {},
                this.handleTouchScrollProxy = ia(this, "handleTouchScroll"),
                this.initMouseIgnoring(500)
            },
            delay                  : null,
            delayTimeoutId         : null,
            destroyHrefHack        : function () {
                var a = this.subjectEl,
                    b = this.subjectHref;
                setTimeout(function () {
                    b && a.attr("href", b)
                }, 0)
            },
            endDrag                : function (a) {
                this.isDragging && (this.isDragging = !1, this.handleDragEnd(a))
            },
            endInteraction         : function (a, b) {
                this.isInteracting && (this.endDrag(a), this.delayTimeoutId && (clearTimeout(this.delayTimeoutId), this.delayTimeoutId = null), this.destroyAutoScroll(), this.unbindHandlers(), this.isInteracting = !1, this.handleInteractionEnd(a, b), this.isTouch && this.tempIgnoreMouse())
            },
            handleDelayEnd         : function (a) {
                this.isDelayEnded = !0,
                this.isDistanceSurpassed && this.startDrag(a)
            },
            handleDistanceSurpassed: function (a) {
                this.isDistanceSurpassed = !0,
                this.isDelayEnded && this.startDrag(a)
            },
            handleDrag             : function (a, b, c) {
                this.trigger("drag", a, b, c),
                this.updateAutoScroll(c)
            },
            handleDragEnd          : function (a) {
                this.trigger("dragEnd", a),
                this.destroyHrefHack()
            },
            handleDragStart        : function (a) {
                this.trigger("dragStart", a),
                this.initHrefHack()
            },
            handleInteractionEnd   : function (a, b) {
                this.trigger("interactionEnd", a, b || !1)
            },
            handleInteractionStart : function (a) {
                this.trigger("interactionStart", a)
            },
            handleMouseMove        : function (a) {
                this.handleMove(a)
            },
            handleMove             : function (a) {
                var b,
                    c = v(a) - this.originX,
                    d = w(a) - this.originY,
                    e = this.minDistance;
                this.isDistanceSurpassed || (b = c * c + d * d, b >= e * e && this.handleDistanceSurpassed(a)),
                this.isDragging && this.handleDrag(c, d, a)
            },
            handleTouchMove        : function (a) {
                this.isDragging && a.preventDefault(),
                this.handleMove(a)
            },
            handleTouchScroll      : function (a) {
                this.isDragging || this.endInteraction(a, !0)
            },
            handleTouchScrollProxy : null,
            initHrefHack           : function () {
                var a = this.subjectEl;
                (this.subjectHref = a
                    ? a.attr("href")
                    : null) && a.removeAttr("href")
            },
            isDelayEnded           : !1,
            isDistanceSurpassed    : !1,
            isDragging             : !1,
            isInteracting          : !1,
            isTouch                : !1,
            minDistance            : null,
            options                : null,
            originX                : null,
            originY                : null,
            scrollEl               : null,
            startDelay             : function (a) {
                var b = this;
                this.delay
                    ? this.delayTimeoutId = setTimeout(function () {
                        b.handleDelayEnd(a)
                    }, this.delay)
                    : this.handleDelayEnd(a)
            },
            startDrag              : function (a, b) {
                this.startInteraction(a, b),
                this.isDragging || (this.isDragging = !0, this.handleDragStart(a))
            },
            startInteraction       : function (b, c) {
                var d = x(b);
                if ("mousedown" === b.type) {
                    if (this.isIgnoringMouse) 
                        return;
                    if (!u(b)) 
                        return;
                    b.preventDefault()
                }
                this.isInteracting || (c = c || {}, this.delay = ba(c.delay, this.options.delay, 0), this.minDistance = ba(c.distance, this.options.distance, 0), this.subjectEl = this.options.subjectEl, this.isInteracting = !0, this.isTouch = d, this.isDelayEnded = !1, this.isDistanceSurpassed = !1, this.originX = v(b), this.originY = w(b), this.scrollEl = m(a(b.target)), this.bindHandlers(), this.initAutoScroll(), this.handleInteractionStart(b), this.startDelay(b), this.minDistance || this.handleDistanceSurpassed(b))
            },
            subjectEl              : null,
            subjectHref            : null,
            trigger                : function (a) {
                this.options[a] && this
                    .options[a]
                    .apply(this, Array.prototype.slice.call(arguments, 1)),
                this["_" + a] && this["_" + a].apply(this, Array.prototype.slice.call(arguments, 1))
            },
            unbindHandlers         : function () {
                this.stopListeningTo(a(document)),
                B(this.handleTouchScrollProxy),
                this.scrollEl && this.stopListeningTo(this.scrollEl, "scroll")
            }
        });
    qb.mixin({
        computeScrollBounds  : function () {
            this.isAutoScroll && (this.scrollBounds = n(this.scrollEl))
        },
        constrainScrollVel   : function () {
            var a = this.scrollEl;
            this.scrollTopVel < 0
                ? a.scrollTop() <= 0 && (this.scrollTopVel = 0)
                : this.scrollTopVel > 0 && a.scrollTop() + a[0].clientHeight >= a[0].scrollHeight && (this.scrollTopVel = 0),
            this.scrollLeftVel < 0
                ? a.scrollLeft() <= 0 && (this.scrollLeftVel = 0)
                : this.scrollLeftVel > 0 && a.scrollLeft() + a[0].clientWidth >= a[0].scrollWidth && (this.scrollLeftVel = 0)
        },
        destroyAutoScroll    : function () {
            this.endAutoScroll(),
            this.isAutoScroll && this.stopListeningTo(this.scrollEl, "scroll")
        },
        endAutoScroll        : function () {
            this.scrollIntervalId && (clearInterval(this.scrollIntervalId), this.scrollIntervalId = null, this.handleScrollEnd())
        },
        handleDebouncedScroll: function () {
            this.scrollIntervalId || this.handleScrollEnd()
        },
        handleScrollEnd      : function () {},
        initAutoScroll       : function () {
            var a = this.scrollEl;
            this.isAutoScroll = this.options.scroll && a && !a.is(window) && !a.is(document),
            this.isAutoScroll && this.listenTo(a, "scroll", ja(this.handleDebouncedScroll, 100))
        },
        isAutoScroll         : !1,
        scrollBounds         : null,
        scrollIntervalFunc   : function () {
            var a = this.scrollEl,
                b = this.scrollIntervalMs / 1e3;
            this.scrollTopVel && a.scrollTop(a.scrollTop() + this.scrollTopVel * b),
            this.scrollLeftVel && a.scrollLeft(a.scrollLeft() + this.scrollLeftVel * b),
            this.constrainScrollVel(),
            this.scrollTopVel || this.scrollLeftVel || this.endAutoScroll()
        },
        scrollIntervalId     : null,
        scrollIntervalMs     : 50,
        scrollLeftVel        : null,
        scrollSensitivity    : 30,
        scrollSpeed          : 200,
        scrollTopVel         : null,
        setScrollVel         : function (a, b) {
            this.scrollTopVel  = a,
            this.scrollLeftVel = b,
            this.constrainScrollVel(),
            !this.scrollTopVel && !this.scrollLeftVel || this.scrollIntervalId || (this.scrollIntervalId = setInterval(ia(this, "scrollIntervalFunc"), this.scrollIntervalMs))
        },
        updateAutoScroll     : function (a) {
            var b,
                c,
                d,
                e,
                f = this.scrollSensitivity,
                g = this.scrollBounds,
                h = 0,
                i = 0;
            g && (b = (f - (w(a) - g.top)) / f, c = (f - (g.bottom - w(a))) / f, d = (f - (v(a) - g.left)) / f, e = (f - (g.right - v(a))) / f, b >= 0 && 1 >= b
                ? h = b * this.scrollSpeed * -1
                : c >= 0 && 1 >= c && (h = c * this.scrollSpeed), d >= 0 && 1 >= d
                ? i = d * this.scrollSpeed * -1
                : e >= 0 && 1 >= e && (i = e * this.scrollSpeed)),
            this.setScrollVel(h, i)
        }
    });
    var rb = qb.extend({
            component             : null,
            computeCoords         : function () {
                this
                    .component
                    .prepareHits(),
                this.computeScrollBounds()
            },
            constructor           : function (a, b) {
                qb.call(this, b),
                this.component = a
            },
            coordAdjust           : null,
            handleDrag            : function (a, b, c) {
                var d;
                qb
                    .prototype
                    .handleDrag
                    .apply(this, arguments),
                d = this.queryHit(v(c), w(c)),
                Ba(d, this.hit) || (this.hit && this.handleHitOut(), d && this.handleHitOver(d))
            },
            handleDragEnd         : function () {
                this.handleHitDone(),
                qb
                    .prototype
                    .handleDragEnd
                    .apply(this, arguments)
            },
            handleDragStart       : function (a) {
                var b;
                qb
                    .prototype
                    .handleDragStart
                    .apply(this, arguments),
                b = this.queryHit(v(a), w(a)),
                b && this.handleHitOver(b)
            },
            handleHitDone         : function () {
                this.hit && this.trigger("hitDone", this.hit)
            },
            handleHitOut          : function () {
                this.hit && (this.trigger("hitOut", this.hit), this.handleHitDone(), this.hit = null)
            },
            handleHitOver         : function (a) {
                var b = Ba(a, this.origHit);
                this.hit = a,
                this.trigger("hitOver", this.hit, b, this.origHit)
            },
            handleInteractionEnd  : function () {
                qb
                    .prototype
                    .handleInteractionEnd
                    .apply(this, arguments),
                this.origHit = null,
                this.hit     = null,
                this
                    .component
                    .releaseHits()
            },
            handleInteractionStart: function (a) {
                var b,
                    c,
                    d,
                    e = this.subjectEl;
                this.computeCoords(),
                a
                    ? (c = {
                        left: v(a),
                        top : w(a)
                    }, d = c, e && (b = n(e), d = D(d, b)), this.origHit = this.queryHit(d.left, d.top), e && this.options.subjectCenter && (this.origHit && (b = C(this.origHit, b) || b), d = E(b)), this.coordAdjust = F(d, c))
                    : (this.origHit = null, this.coordAdjust = null),
                qb
                    .prototype
                    .handleInteractionStart
                    .apply(this, arguments)
            },
            handleScrollEnd       : function () {
                qb
                    .prototype
                    .handleScrollEnd
                    .apply(this, arguments),
                this.computeCoords()
            },
            hit                   : null,
            origHit               : null,
            queryHit              : function (a, b) {
                return this.coordAdjust && (a += this.coordAdjust.left, b += this.coordAdjust.top),
                this
                    .component
                    .queryHit(a, b)
            }
        }),
        sb = ya.extend(mb, {
            constructor   : function (b, c) {
                this.options  = c = c || {},
                this.sourceEl = b,
                this.parentEl = c.parentEl
                    ? a(c.parentEl)
                    : b.parent()
            },
            el            : null,
            getEl         : function () {
                var a = this.el;
                return a || (this.sourceEl.width(), a = this.el = this.sourceEl.clone().addClass(this.options.additionalClass || "").css({
                    bottom    : "auto",
                    display   : this.isHidden
                        ? "none"
                        : "",
                    height    : this
                        .sourceEl
                        .height(),
                    margin    : 0,
                    opacity   : this.options.opacity || "",
                    position  : "absolute",
                    right     : "auto",
                    visibility: "",
                    width     : this
                        .sourceEl
                        .width(),
                    zIndex    : this.options.zIndex
                }), a.addClass("fc-unselectable"), a.appendTo(this.parentEl)),
                a
            },
            handleMove    : function (a) {
                this.topDelta  = w(a) - this.y0,
                this.leftDelta = v(a) - this.x0,
                this.isHidden || this.updatePosition()
            },
            hide          : function () {
                this.isHidden || (this.isHidden = !0, this.el && this.el.hide())
            },
            isAnimating   : !1,
            isFollowing   : !1,
            isHidden      : !1,
            left0         : null,
            leftDelta     : null,
            options       : null,
            parentEl      : null,
            removeElement : function () {
                this.el && (this.el.remove(), this.el = null)
            },
            show          : function () {
                this.isHidden && (this.isHidden = !1, this.updatePosition(), this.getEl().show())
            },
            sourceEl      : null,
            start         : function (b) {
                this.isFollowing || (this.isFollowing = !0, this.y0 = w(b), this.x0 = v(b), this.topDelta = 0, this.leftDelta = 0, this.isHidden || this.updatePosition(), x(b)
                    ? this.listenTo(a(document), "touchmove", this.handleMove)
                    : this.listenTo(a(document), "mousemove", this.handleMove))
            },
            stop          : function (b, c) {
                function d() {
                    this.isAnimating = !1,
                    e.removeElement(),
                    this.top0 = this.left0 = null,
                    c && c()
                }
                var e = this,
                    f = this.options.revertDuration;
                this.isFollowing && !this.isAnimating && (this.isFollowing = !1, this.stopListeningTo(a(document)), b && f && !this.isHidden
                    ? (this.isAnimating = !0, this.el.animate({
                        left: this.left0,
                        top : this.top0
                    }, {
                        complete: d,
                        duration: f
                    }))
                    : d())
            },
            top0          : null,
            topDelta      : null,
            updatePosition: function () {
                var a,
                    b;
                this.getEl(),
                null === this.top0 && (this.sourceEl.width(), a = this.sourceEl.offset(), b = this.el.offsetParent().offset(), this.top0 = a.top - b.top, this.left0 = a.left - b.left),
                this
                    .el
                    .css({
                        left: this.left0 + this.leftDelta,
                        top : this.top0 + this.topDelta
                    })
            },
            x0            : null,
            y0            : null
        }),
        tb = Wa.Grid = ya.extend(mb, nb, {
            bindDayHandler           : function (b, c) {
                var d = this;
                this
                    .el
                    .on(b, function (b) {
                        return a(b.target).is(".fc-event-container *, .fc-more") || a(b.target)
                            .closest(".fc-popover")
                            .length
                            ? void 0
                            : c.call(d, b)
                    })
            },
            bindGlobalHandlers       : function () {
                this.listenTo(a(document), {
                    dragstart: this.externalDragStart,
                    sortstart: this.externalDragStart
                })
            },
            buildDayDragListener     : function () {
                var a,
                    b,
                    c = this,
                    d = this.view,
                    e = d.opt("selectable"),
                    f = new rb(this, {
                        dragStart       : function () {
                            d.unselect()
                        },
                        hitOut          : function () {
                            a = null,
                            b = null,
                            c.unrenderSelection(),
                            h()
                        },
                        hitOver         : function (d, f, h) {
                            h && (f || (a = null), e && (b = c.computeSelection(c.getHitSpan(h), c.getHitSpan(d)), b
                                ? c.renderSelection(b)
                                : b === !1 && g()))
                        },
                        interactionEnd  : function (e, f) {
                            f || (a && !c.isIgnoringMouse && d.triggerDayClick(c.getHitSpan(a), c.getHitEl(a), e), b && d.reportSelection(b, e), h())
                        },
                        interactionStart: function () {
                            a = f.origHit
                        },
                        scroll          : d.opt("dragScroll")
                    });
                return f
            },
            clearDragListeners       : function () {
                this
                    .dayDragListener
                    .endInteraction(),
                this.segDragListener && this
                    .segDragListener
                    .endInteraction(),
                this.segResizeListener && this
                    .segResizeListener
                    .endInteraction(),
                this.externalDragListener && this
                    .externalDragListener
                    .endInteraction()
            },
            computeDisplayEventEnd   : function () {
                return !0
            },
            computeDisplayEventTime  : function () {
                return !0
            },
            computeEventTimeFormat   : function () {
                return this
                    .view
                    .opt("smallTimeFormat")
            },
            computeSelection         : function (a, b) {
                var c = this.computeSelectionSpan(a, b);
                return c && !this
                    .view
                    .calendar
                    .isSelectionSpanAllowed(c)
                    ? !1
                    : c
            },
            computeSelectionSpan     : function (a, b) {
                var c = [a.start, a.end, b.start, b.end];
                return c.sort(ga), {
                    end  : c[3].clone(),
                    start: c[0].clone()
                }
            },
            constructor              : function (a) {
                this.view            = a,
                this.isRTL           = a.opt("isRTL"),
                this.elsByFill       = {},
                this.dayDragListener = this.buildDayDragListener(),
                this.initMouseIgnoring()
            },
            dayDragListener          : null,
            dayMousedown             : function (a) {
                this.isIgnoringMouse || this
                    .dayDragListener
                    .startInteraction(a, {})
            },
            dayTouchStart            : function (a) {
                var b = this.view;
                (b.isSelected || b.selectedEvent) && this.tempIgnoreMouse(),
                this
                    .dayDragListener
                    .startInteraction(a, {
                        delay: this
                            .view
                            .opt("longPressDelay")
                    })
            },
            diffDates                : function (a, b) {
                return this.largeUnit
                    ? N(a, b, this.largeUnit)
                    : L(a, b)
            },
            displayEventEnd          : null,
            displayEventTime         : null,
            el                       : null,
            elsByFill                : null,
            end                      : null,
            eventTimeFormat          : null,
            externalDragListener     : null,
            fabricateHelperEvent     : function (a, b) {
                var c = b
                    ? X(b.event)
                    : {};
                return c.start = a
                    .start
                    .clone(),
                c.end          = a.end
                    ? a
                        .end
                        .clone()
                    : null,
                c.allDay       = null,
                this
                    .view
                    .calendar
                    .normalizeEventDates(c),
                c.className = (c.className || []).concat("fc-helper"),
                b || (c.editable = !1),
                c
            },
            fillSegHtml              : function (a, b) {
                var c = this[a + "SegClasses"],
                    d = this[a + "SegCss"],
                    e = c
                        ? c.call(this, b)
                        : [],
                    f = ea(d
                        ? d.call(this, b)
                        : {});
                return "<" + this.fillSegTag + (e.length
                    ? ' class="' + e.join(" ") + '"'
                    : "") + (f
                    ? ' style="' + f + '"'
                    : "") + " />"
            },
            fillSegTag               : "div",
            getDayClasses            : function (a) {
                var b = this.view,
                    c = b
                        .calendar
                        .getNow(),
                    d = ["fc-" + $a[a.day()]];
                return 1 == b
                    .intervalDuration
                    .as("months") && a.month() != b
                    .intervalStart
                    .month() && d.push("fc-other-month"),
                a.isSame(c, "day")
                    ? d.push("fc-today", b.highlightStateClass)
                    : c > a
                        ? d.push("fc-past")
                        : d.push("fc-future"),
                d
            },
            getHitEl                 : function (a) {},
            getHitSpan               : function (a) {},
            getNowIndicatorUnit      : function () {},
            highlightSegClasses      : function () {
                return ["fc-highlight"]
            },
            isRTL                    : null,
            largeUnit                : null,
            minResizeDuration        : null,
            prepareHits              : function () {},
            processRangeOptions      : function () {
                var a,
                    b,
                    c = this.view;
                this.eventTimeFormat  = c.opt("eventTimeFormat") || c.opt("timeFormat") || this.computeEventTimeFormat(),
                a                     = c.opt("displayEventTime"),
                null == a && (a = this.computeDisplayEventTime()),
                b                     = c.opt("displayEventEnd"),
                null == b && (b = this.computeDisplayEventEnd()),
                this.displayEventTime = a,
                this.displayEventEnd  = b
            },
            queryHit                 : function (a, b) {},
            rangeUpdated             : function () {},
            releaseHits              : function () {},
            removeElement            : function () {
                this.unbindGlobalHandlers(),
                this.clearDragListeners(),
                this
                    .el
                    .remove()
            },
            renderBusinessHours      : function () {},
            renderDates              : function () {},
            renderEventLocationHelper: function (a, b) {
                var c = this.fabricateHelperEvent(a, b);
                return this.renderHelper(c, b)
            },
            renderFill               : function (a, b) {},
            renderFillSegEls         : function (b, c) {
                var d,
                    e = this,
                    f = this[b + "SegEl"],
                    g = "",
                    h = [];
                if (c.length) {
                    for (d = 0; d < c.length; d++) 
                        g += this.fillSegHtml(b, c[d]);
                    a(g)
                        .each(function (b, d) {
                            var g = c[b],
                                i = a(d);
                            f && (i = f.call(e, g, i)),
                            i && (i = a(i), i.is(e.fillSegTag) && (g.el = i, h.push(g)))
                        })
                }
                return h
            },
            renderHelper             : function (a, b) {},
            renderHighlight          : function (a) {
                this.renderFill("highlight", this.spanToSegs(a))
            },
            renderNowIndicator       : function (a) {},
            renderSelection          : function (a) {
                this.renderHighlight(a)
            },
            renderSkeleton           : function () {},
            segDragListener          : null,
            segResizeListener        : null,
            setElement               : function (a) {
                this.el = a,
                y(a),
                this.bindDayHandler("touchstart", this.dayTouchStart),
                this.bindDayHandler("mousedown", this.dayMousedown),
                this.bindSegHandlers(),
                this.bindGlobalHandlers()
            },
            setRange                 : function (a) {
                this.start = a
                    .start
                    .clone(),
                this.end   = a
                    .end
                    .clone(),
                this.rangeUpdated(),
                this.processRangeOptions()
            },
            spanToSegs               : function (a) {},
            start                    : null,
            unbindGlobalHandlers     : function () {
                this.stopListeningTo(a(document))
            },
            unrenderBusinessHours    : function () {},
            unrenderDates            : function () {},
            unrenderFill             : function (a) {
                var b = this.elsByFill[a];
                b && (b.remove(), delete this.elsByFill[a])
            },
            unrenderHelper           : function () {},
            unrenderHighlight        : function () {
                this.unrenderFill("highlight")
            },
            unrenderNowIndicator     : function () {},
            unrenderSelection        : function () {
                this.unrenderHighlight()
            },
            view                     : null
        });
    tb.mixin({
        applyDragOpacity       : function (a) {
            var b = this
                .view
                .opt("dragOpacity");
            null != b && a.each(function (a, c) {
                c.style.opacity = b
            })
        },
        bgEventSegClasses      : function (a) {
            var b = a.event,
                c = b.source || {};
            return ["fc-bgevent"].concat(b.className, c.className || [])
        },
        bgEventSegCss          : function (a) {
            return {
                "background-color": this.getSegSkinCss(a)["background-color"]
            }
        },
        bgEventSegEl           : function (a, b) {
            return this
                .view
                .resolveEventEl(a.event, b)
        },
        bindSegHandler         : function (b, c) {
            var d = this;
            this
                .el
                .on(b, ".fc-event-container > *", function (b) {
                    var e = a(this).data("fc-seg");
                    return !e || d.isDraggingSeg || d.isResizingSeg
                        ? void 0
                        : c.call(d, e, b)
                })
        },
        bindSegHandlers        : function () {
            this.bindSegHandler("touchstart", this.handleSegTouchStart),
            this.bindSegHandler("touchend", this.handleSegTouchEnd),
            this.bindSegHandler("mouseenter", this.handleSegMouseover),
            this.bindSegHandler("mouseleave", this.handleSegMouseout),
            this.bindSegHandler("mousedown", this.handleSegMousedown),
            this.bindSegHandler("click", this.handleSegClick)
        },
        buildSegDragListener   : function (a) {
            var b,
                c,
                d,
                e = this,
                f = this.view,
                i = f.calendar,
                j = a.el,
                k = a.event;
            if (this.segDragListener) 
                return this.segDragListener;
            var l = this.segDragListener = new rb(f, {
                dragStart       : function (c) {
                    l.isTouch && !f.isEventSelected(k) && f.selectEvent(k),
                    b = !0,
                    e.handleSegMouseout(a, c),
                    e.segDragStart(a, c),
                    f.hideEvent(k)
                },
                hitDone         : function () {
                    h()
                },
                hitOut          : function () {
                    f.unrenderDrag(),
                    c.show(),
                    d = null
                },
                hitOver         : function (b, h, j) {
                    var m;
                    a.hit && (j = a.hit),
                    d = e.computeEventDrop(j.component.getHitSpan(j), b.component.getHitSpan(b), k),
                    d && !i.isEventSpanAllowed(e.eventToSpan(d), k) && (g(), d = null),
                    d && (m = f.renderDrag(d, a))
                        ? (m.addClass("fc-dragging"), l.isTouch || e.applyDragOpacity(m), c.hide())
                        : c.show(),
                    h && (d = null)
                },
                interactionEnd  : function (g) {
                    c
                        .stop(!d, function () {
                            b && (f.unrenderDrag(), f.showEvent(k), e.segDragStop(a, g)),
                            d && f.reportEventDrop(k, d, this.largeUnit, j, g)
                        }),
                    e.segDragListener = null
                },
                interactionStart: function (d) {
                    b = !1,
                    c = new sb(a.el, {
                        additionalClass: "fc-dragging",
                        opacity        : l.isTouch
                            ? null
                            : f.opt("dragOpacity"),
                        parentEl       : f.el,
                        revertDuration : f.opt("dragRevertDuration"),
                        zIndex         : 2
                    }),
                    c.hide(),
                    c.start(d)
                },
                scroll          : f.opt("dragScroll"),
                subjectCenter   : !0,
                subjectEl       : j
            });
            return l
        },
        buildSegResizeListener : function (a, b) {
            var c,
                d,
                e = this,
                f = this.view,
                i = f.calendar,
                j = a.el,
                k = a.event,
                l = i.getEventEnd(k),
                m = this.segResizeListener = new rb(this, {
                    dragStart       : function (b) {
                        c = !0,
                        e.handleSegMouseout(a, b),
                        e.segResizeStart(a, b)
                    },
                    hitDone         : function () {
                        e.unrenderEventResize(),
                        f.showEvent(k),
                        h()
                    },
                    hitOut          : function () {
                        d = null
                    },
                    hitOver         : function (c, h, j) {
                        var m = e.getHitSpan(j),
                            n = e.getHitSpan(c);
                        d = b
                            ? e.computeEventStartResize(m, n, k)
                            : e.computeEventEndResize(m, n, k),
                        d && (i.isEventSpanAllowed(e.eventToSpan(d), k)
                            ? d.start.isSame(k.start) && d.end.isSame(l) && (d = null)
                            : (g(), d = null)),
                        d && (f.hideEvent(k), e.renderEventResize(d, a))
                    },
                    interactionEnd  : function (b) {
                        c && e.segResizeStop(a, b),
                        d && f.reportEventResize(k, d, this.largeUnit, j, b),
                        e.segResizeListener = null
                    },
                    interactionStart: function () {
                        c = !1
                    },
                    scroll          : f.opt("dragScroll"),
                    subjectEl       : j
                });
            return m
        },
        buildSegSelectListener : function (a) {
            var b = this,
                c = this.view,
                d = a.event;
            if (this.segDragListener) 
                return this.segDragListener;
            var e = this.segDragListener = new qb({
                dragStart     : function (a) {
                    e.isTouch && !c.isEventSelected(d) && c.selectEvent(d)
                },
                interactionEnd: function (a) {
                    b.segDragListener = null
                }
            });
            return e
        },
        businessHoursSegClasses: function (a) {
            return ["fc-nonbusiness", "fc-bgevent"]
        },
        compareEventSegs       : function (a, b) {
            return a.eventStartMS - b.eventStartMS || b.eventDurationMS - a.eventDurationMS || b.event.allDay - a.event.allDay || H(a.event, b.event, this.view.eventOrderSpecs)
        },
        computeEventDrop       : function (a, b, c) {
            var d,
                e,
                f = this.view.calendar,
                g = a.start,
                h = b.start;
            return g.hasTime() === h.hasTime()
                ? (d = this.diffDates(h, g), c.allDay && T(d)
                    ? (e = {
                        allDay: !1,
                        end   : f.getEventEnd(c),
                        start : c
                            .start
                            .clone()
                    }, f.normalizeEventTimes(e))
                    : e = {
                        allDay: c.allDay,
                        end   : c.end
                            ? c
                                .end
                                .clone()
                            : null,
                        start : c
                            .start
                            .clone()
                    }, e.start.add(d), e.end && e.end.add(d))
                : e = {
                    allDay: !h.hasTime(),
                    end   : null,
                    start : h.clone()
                },
            e
        },
        computeEventEndResize  : function (a, b, c) {
            return this.computeEventResize("end", a, b, c)
        },
        computeEventResize     : function (a, b, c, d) {
            var e,
                f,
                g = this.view.calendar,
                h = this.diffDates(c[a], b[a]);
            return e = {
                allDay: d.allDay,
                end   : g.getEventEnd(d),
                start : d
                    .start
                    .clone()
            },
            e.allDay && T(h) && (e.allDay = !1, g.normalizeEventTimes(e)),
            e[a].add(h),
            e
                .start
                .isBefore(e.end) || (f = this.minResizeDuration || (d.allDay
                ? g.defaultAllDayEventDuration
                : g.defaultTimedEventDuration), "start" == a
                ? e.start = e.end.clone().subtract(f)
                : e.end = e.start.clone().add(f)),
            e
        },
        computeEventStartResize: function (a, b, c) {
            return this.computeEventResize("start", a, b, c)
        },
        computeExternalDrop    : function (a, b) {
            var c = this.view.calendar,
                d = {
                    end  : null,
                    start: c.applyTimezone(a.start)
                };
            return b.startTime && !d
                .start
                .hasTime() && d
                .start
                .time(b.startTime),
            b.duration && (d.end = d.start.clone().add(b.duration)),
            d
        },
        eventRangeToSegs       : function (a, b, c) {
            var d,
                e = this.eventRangeToSpans(a, b),
                f = [];
            for (d = 0; d < e.length; d++) 
                f
                    .push
                    .apply(f, this.eventSpanToSegs(e[d], b, c));
            return f
        },
        eventRangeToSpans      : function (b, c) {
            return [a.extend({}, b)]
        },
        eventSpanToSegs        : function (a, b, c) {
            var d,
                e,
                f = c
                    ? c(a)
                    : this.spanToSegs(a);
            for (d = 0; d < f.length; d++) 
                e                 = f[d],
                e.event           = b,
                e.eventStartMS    =+ a.start,
                e.eventDurationMS = a.end - a.start;
            return f
        },
        eventsToSegs           : function (b, c) {
            var d = this,
                e = Ga(b),
                f = [];
            return a.each(e, function (a, b) {
                var e,
                    g = [];
                for (e = 0; e < b.length; e++) 
                    g.push(d.eventToRange(b[e]));
                if (Ea(b[0])) 
                    for (g = d.invertRanges(g), e = 0; e < g.length; e++) 
                        f
                            .push
                            .apply(f, d.eventRangeToSegs(g[e], b[0], c));
                else 
                    for (e = 0; e < g.length; e++) 
                        f
                            .push
                            .apply(f, d.eventRangeToSegs(g[e], b[e], c))
                }),
            f
        },
        eventToRange           : function (a) {
            return {
                end  : (a.end
                    ? a.end.clone()
                    : this.view.calendar.getDefaultEventEnd(null != a.allDay
                        ? a.allDay
                        : !a.start.hasTime(), a.start)).stripZone(),
                start: a
                    .start
                    .clone()
                    .stripZone()
            }
        },
        eventToSegs            : function (a) {
            return this.eventsToSegs([a])
        },
        eventToSpan            : function (a) {
            return this.eventToSpans(a)[0]
        },
        eventToSpans           : function (a) {
            var b = this.eventToRange(a);
            return this.eventRangeToSpans(b, a)
        },
        externalDragStart      : function (b, c) {
            var d,
                e,
                f = this.view;
            f.opt("droppable") && (d = a((c
                ? c.item
                : null) || b.target), e = f.opt("dropAccept"), (a.isFunction(e)
                ? e.call(d[0], d)
                : d.is(e)) && (this.isDraggingExternal || this.listenToExternalDrag(d, b, c)))
        },
        fgSegHtml              : function (a, b) {},
        getEventSegs           : function () {
            return this.segs || []
        },
        getEventTimeText       : function (a, b, c) {
            return null == b && (b = this.eventTimeFormat),
            null == c && (c = this.displayEventEnd),
            this.displayEventTime && a
                .start
                .hasTime()
                ? c && a.end
                    ? this
                        .view
                        .formatRange(a, b)
                    : a
                        .start
                        .format(b)
                : ""
        },
        getSegClasses          : function (a, b, c) {
            var d = this.view,
                e = a.event,
                f = [
                    "fc-event", a.isStart
                        ? "fc-start"
                        : "fc-not-start",
                    a.isEnd
                        ? "fc-end"
                        : "fc-not-end"
                ].concat(e.className, e.source
                    ? e.source.className
                    : []);
            return b && f.push("fc-draggable"),
            c && f.push("fc-resizable"),
            d.isEventSelected(e) && f.push("fc-selected"),
            f
        },
        getSegSkinCss          : function (a) {
            var b = a.event,
                c = this.view,
                d = b.source || {},
                e = b.color,
                f = d.color,
                g = c.opt("eventColor");
            return {
                "background-color": b.backgroundColor || e || d.backgroundColor || f || c.opt("eventBackgroundColor") || g,
                "border-color"    : b.borderColor || e || d.borderColor || f || c.opt("eventBorderColor") || g,
                color             : b.textColor || d.textColor || c.opt("eventTextColor")
            }
        },
        handleSegClick         : function (a, b) {
            return this
                .view
                .trigger("eventClick", a.el[0], a.event, b)
        },
        handleSegMousedown     : function (a, b) {
            var c = this.startSegResize(a, b, {distance: 5});
            !c && this
                .view
                .isEventDraggable(a.event) && this
                .buildSegDragListener(a)
                .startInteraction(b, {distance: 5})
        },
        handleSegMouseout      : function (a, b) {
            b = b || {},
            this.mousedOverSeg && (a = a || this.mousedOverSeg, this.mousedOverSeg = null, a.el.removeClass("fc-allow-mouse-resize"), this.view.trigger("eventMouseout", a.el[0], a.event, b))
        },
        handleSegMouseover     : function (a, b) {
            this.isIgnoringMouse || this.mousedOverSeg || (this.mousedOverSeg = a, a.el.addClass("fc-allow-mouse-resize"), this.view.trigger("eventMouseover", a.el[0], a.event, b))
        },
        handleSegTouchEnd      : function (a, b) {
            this.tempIgnoreMouse()
        },
        handleSegTouchStart    : function (a, b) {
            var c,
                d = this.view,
                e = a.event,
                f = d.isEventSelected(e),
                g = d.isEventDraggable(e),
                h = d.isEventResizable(e),
                i = !1;
            f && h && (i = this.startSegResize(a, b)),
            i || !g && !h || (c = g
                ? this.buildSegDragListener(a)
                : this.buildSegSelectListener(a), c.startInteraction(b, {
                delay: f
                    ? 0
                    : this
                        .view
                        .opt("longPressDelay")
            })),
            this.tempIgnoreMouse()
        },
        invertRanges           : function (a) {
            var b,
                c,
                d = this.view,
                e = d
                    .start
                    .clone(),
                f = d
                    .end
                    .clone(),
                g = [],
                h = e;
            for (a.sort(Ha), b = 0; b < a.length; b++) 
                c = a[b],
                c.start > h && g.push({end: c.start, start: h}),
                h = c.end;
            return f > h && g.push({end: f, start: h}),
            g
        },
        isDraggingExternal     : !1,
        isDraggingSeg          : !1,
        isResizingSeg          : !1,
        listenToExternalDrag   : function (a, b, c) {
            var d,
                e = this,
                f = this.view.calendar,
                i = Ia(a),
                j = e.externalDragListener = new rb(this, {
                    hitDone         : function () {
                        h(),
                        e.unrenderDrag()
                    },
                    hitOut          : function () {
                        d = null
                    },
                    hitOver         : function (a) {
                        d = e.computeExternalDrop(a.component.getHitSpan(a), i),
                        d && !f.isExternalSpanAllowed(e.eventToSpan(d), d, i.eventProps) && (g(), d = null),
                        d && e.renderDrag(d)
                    },
                    interactionEnd  : function (b) {
                        d && e
                            .view
                            .reportExternalDrop(i, d, a, b, c),
                        e.isDraggingExternal   = !1,
                        e.externalDragListener = null
                    },
                    interactionStart: function () {
                        e.isDraggingExternal = !0
                    }
                });
            j.startDrag(b)
        },
        mousedOverSeg          : null,
        renderBgEvents         : function (a) {
            var b = this.eventsToSegs(a);
            return this.renderBgSegs(b) || b
        },
        renderBgSegs           : function (a) {
            return this.renderFill("bgEvent", a)
        },
        renderDrag             : function (a, b) {},
        renderEventResize      : function (a, b) {},
        renderEvents           : function (a) {
            var b,
                c = [],
                d = [];
            for (b = 0; b < a.length; b++) 
                (Da(a[b])
                    ? c
                    : d).push(a[b]);
            this.segs = [].concat(this.renderBgEvents(c), this.renderFgEvents(d))
        },
        renderFgEvents         : function (a) {
            var b = this.eventsToSegs(a);
            return this.renderFgSegs(b) || b
        },
        renderFgSegEls         : function (b, c) {
            var d,
                e = this.view,
                f = "",
                g = [];
            if (b.length) {
                for (d = 0; d < b.length; d++) 
                    f += this.fgSegHtml(b[d], c);
                a(f)
                    .each(function (c, d) {
                        var f = b[c],
                            h = e.resolveEventEl(f.event, a(d));
                        h && (h.data("fc-seg", f), f.el = h, g.push(f))
                    })
            }
            return g
        },
        renderFgSegs           : function (a) {},
        segDragStart           : function (a, b) {
            this.isDraggingSeg = !0,
            this
                .view
                .trigger("eventDragStart", a.el[0], a.event, b, {})
        },
        segDragStop            : function (a, b) {
            this.isDraggingSeg = !1,
            this
                .view
                .trigger("eventDragStop", a.el[0], a.event, b, {})
        },
        segResizeStart         : function (a, b) {
            this.isResizingSeg = !0,
            this
                .view
                .trigger("eventResizeStart", a.el[0], a.event, b, {})
        },
        segResizeStop          : function (a, b) {
            this.isResizingSeg = !1,
            this
                .view
                .trigger("eventResizeStop", a.el[0], a.event, b, {})
        },
        segs                   : null,
        sortEventSegs          : function (a) {
            a.sort(ia(this, "compareEventSegs"))
        },
        startSegResize         : function (b, c, d) {
            return a(c.target).is(".fc-resizer")
                ? (this.buildSegResizeListener(b, a(c.target).is(".fc-start-resizer")).startInteraction(c, d), !0)
                : !1
        },
        unrenderBgSegs         : function () {
            this.unrenderFill("bgEvent")
        },
        unrenderDrag           : function () {},
        unrenderEventResize    : function () {},
        unrenderEvents         : function () {
            this.handleSegMouseout(),
            this.clearDragListeners(),
            this.unrenderFgSegs(),
            this.unrenderBgSegs(),
            this.segs = null
        },
        unrenderFgSegs         : function () {}
    }),
    Wa.isBgEvent      = Da,
    Wa.dataAttrPrefix = "";
    var ub = Wa.DayTableMixin = {
            bookendCells           : function (a) {
                var b = this.renderIntroHtml();
                b && (this.isRTL
                    ? a.append(b)
                    : a.prepend(b))
            },
            breakOnWeeks           : !1,
            colCnt                 : null,
            colHeadFormat          : null,
            computeColCnt          : function () {
                return this.daysPerRow
            },
            computeColHeadFormat   : function () {
                return this.rowCnt > 1 || this.colCnt > 10
                    ? "ddd"
                    : this.colCnt > 1
                        ? this
                            .view
                            .opt("dayOfMonthFormat")
                        : "dddd"
            },
            dayDates               : null,
            dayIndices             : null,
            daysPerRow             : null,
            getCellDate            : function (a, b) {
                return this
                    .dayDates[this.getCellDayIndex(a, b)]
                    .clone()
            },
            getCellDayIndex        : function (a, b) {
                return a * this.daysPerRow + this.getColDayIndex(b)
            },
            getCellRange           : function (a, b) {
                var c = this.getCellDate(a, b),
                    d = c
                        .clone()
                        .add(1, "days");
                return {end: d, start: c}
            },
            getColDayIndex         : function (a) {
                return this.isRTL
                    ? this.colCnt - 1 - a
                    : a
            },
            getDateDayIndex        : function (a) {
                var b = this.dayIndices,
                    c = a.diff(this.start, "days");
                return 0 > c
                    ? b[0] - 1
                    : c >= b.length
                        ? b[b.length - 1] + 1
                        : b[c]
            },
            renderBgCellHtml       : function (a, b) {
                var c = this.view,
                    d = this.getDayClasses(a);
                return d.unshift("fc-day", c.widgetContentClass),
                '<td class="' + d.join(" ") + '" data-date="' + a.format("YYYY-MM-DD") + '"' + (b
                    ? " " + b
                    : "") + "></td>"
            },
            renderBgCellsHtml      : function (a) {
                var b,
                    c,
                    d = [];
                for (b = 0; b < this.colCnt; b++) 
                    c = this.getCellDate(a, b),
                    d.push(this.renderBgCellHtml(c));
                return d.join("")
            },
            renderBgIntroHtml      : function (a) {
                return this.renderIntroHtml()
            },
            renderBgTrHtml         : function (a) {
                return "<tr>" + (this.isRTL
                    ? ""
                    : this.renderBgIntroHtml(a)) + this.renderBgCellsHtml(a) + (this.isRTL
                    ? this.renderBgIntroHtml(a)
                    : "") + "</tr>"
            },
            renderHeadDateCellHtml : function (a, b, c) {
                var d = this.view;
                return '<th class="fc-day-header ' + d.widgetHeaderClass + " fc-" + $a[a.day()] + '"' + (1 == this.rowCnt
                    ? ' data-date="' + a.format("YYYY-MM-DD") + '"'
                    : "") + (b > 1
                    ? ' colspan="' + b + '"'
                    : "") + (c
                    ? " " + c
                    : "") + ">" + ca(a.format(this.colHeadFormat)) + "</th>"
            },
            renderHeadDateCellsHtml: function () {
                var a,
                    b,
                    c = [];
                for (a = 0; a < this.colCnt; a++) 
                    b = this.getCellDate(0, a),
                    c.push(this.renderHeadDateCellHtml(b));
                return c.join("")
            },
            renderHeadHtml         : function () {
                var a = this.view;
                return '<div class="fc-row ' + a.widgetHeaderClass + '"><table><thead>' + this.renderHeadTrHtml() + "</thead></table></div>"
            },
            renderHeadIntroHtml    : function () {
                return this.renderIntroHtml()
            },
            renderHeadTrHtml       : function () {
                return "<tr>" + (this.isRTL
                    ? ""
                    : this.renderHeadIntroHtml()) + this.renderHeadDateCellsHtml() + (this.isRTL
                    ? this.renderHeadIntroHtml()
                    : "") + "</tr>"
            },
            renderIntroHtml        : function () {},
            rowCnt                 : null,
            sliceRangeByDay        : function (a) {
                var b,
                    c,
                    d,
                    e,
                    f,
                    g,
                    h = this.daysPerRow,
                    i = this
                        .view
                        .computeDayRange(a),
                    j = this.getDateDayIndex(i.start),
                    k = this.getDateDayIndex(i.end.clone().subtract(1, "days")),
                    l = [];
                for (b = 0; b < this.rowCnt; b++) 
                    for (c = b * h, d = c + h - 1, e = c; d >= e; e++) 
                        f = Math.max(j, e),
                        g = Math.min(k, e),
                        f = Math.ceil(f),
                        g = Math.floor(g),
                        g >= f && l.push({
                            firstRowDayIndex: f - c,
                            isEnd           : g === k,
                            isStart         : f === j,
                            lastRowDayIndex : g - c,
                            row             : b
                        });
            return l
            },
            sliceRangeByRow        : function (a) {
                var b,
                    c,
                    d,
                    e,
                    f,
                    g = this.daysPerRow,
                    h = this
                        .view
                        .computeDayRange(a),
                    i = this.getDateDayIndex(h.start),
                    j = this.getDateDayIndex(h.end.clone().subtract(1, "days")),
                    k = [];
                for (b = 0; b < this.rowCnt; b++) 
                    c = b * g,
                    d = c + g - 1,
                    e = Math.max(i, c),
                    f = Math.min(j, d),
                    e = Math.ceil(e),
                    f = Math.floor(f),
                    f >= e && k.push({
                        firstRowDayIndex: e - c,
                        isEnd           : f === j,
                        isStart         : e === i,
                        lastRowDayIndex : f - c,
                        row             : b
                    });
                return k
            },
            updateDayTable         : function () {
                for (var a, b, c, d = this.view, e = this.start.clone(), f = -1, g = [], h = []; e.isBefore(this.end);) 
                    d.isHiddenDay(e)
                        ? g.push(f + .5)
                        : (f++, g.push(f), h.push(e.clone())),
                    e.add(1, "days");
                if (this.breakOnWeeks) {
                    for (b = h[0].day(), a = 1; a < h.length && h[a].day() != b; a++) 
                    ;
                    c = Math.ceil(h.length / a)
                } else 
                    c = 1,
                    a = h.length;
                this.dayDates   = h,
                this.dayIndices = g,
                this.daysPerRow = a,
                this.rowCnt     = c,
                this.updateDayTableCols()
            },
            updateDayTableCols     : function () {
                this.colCnt        = this.computeColCnt(),
                this.colHeadFormat = this
                    .view
                    .opt("columnFormat") || this.computeColHeadFormat()
            }
        },
        vb = Wa.DayGrid = tb.extend(ub, {
            bottomCoordPadding    : 0,
            cellEls               : null,
            colCoordCache         : null,
            computeDisplayEventEnd: function () {
                return 1 == this.colCnt
            },
            computeEventTimeFormat: function () {
                return this
                    .view
                    .opt("extraSmallTimeFormat")
            },
            fillSegTag            : "td",
            getCellEl             : function (a, b) {
                return this
                    .cellEls
                    .eq(a * this.colCnt + b)
            },
            getCellHit            : function (a, b) {
                return {
                    bottom   : this
                        .rowCoordCache
                        .getBottomOffset(a),
                    col      : b,
                    component: this,
                    left     : this
                        .colCoordCache
                        .getLeftOffset(b),
                    right    : this
                        .colCoordCache
                        .getRightOffset(b),
                    row      : a,
                    top      : this
                        .rowCoordCache
                        .getTopOffset(a)
                }
            },
            getHitEl              : function (a) {
                return this.getCellEl(a.row, a.col)
            },
            getHitSpan            : function (a) {
                return this.getCellRange(a.row, a.col)
            },
            helperEls             : null,
            numbersVisible        : !1,
            prepareHits           : function () {
                this
                    .colCoordCache
                    .build(),
                this
                    .rowCoordCache
                    .build(),
                this.rowCoordCache.bottoms[this.rowCnt - 1] += this.bottomCoordPadding
            },
            queryHit              : function (a, b) {
                var c = this
                        .colCoordCache
                        .getHorizontalIndex(a),
                    d = this
                        .rowCoordCache
                        .getVerticalIndex(b);
                return null != d && null != c
                    ? this.getCellHit(d, c)
                    : void 0
            },
            rangeUpdated          : function () {
                this.updateDayTable()
            },
            releaseHits           : function () {
                this
                    .colCoordCache
                    .clear(),
                this
                    .rowCoordCache
                    .clear()
            },
            renderBusinessHours   : function () {
                var a = this
                        .view
                        .calendar
                        .getBusinessHoursEvents(!0),
                    b = this.eventsToSegs(a);
                this.renderFill("businessHours", b, "bgevent")
            },
            renderDates           : function (a) {
                var b,
                    c,
                    d = this.view,
                    e = this.rowCnt,
                    f = this.colCnt,
                    g = "";
                for (b = 0; e > b; b++) 
                    g += this.renderDayRowHtml(b, a);
                for (this.el.html(g), this.rowEls = this.el.find(".fc-row"), this.cellEls = this.el.find(".fc-day"), this.rowCoordCache = new pb({
                    els       : this.rowEls,
                    isVertical: !0
                }), this.colCoordCache = new pb({
                    els         : this
                        .cellEls
                        .slice(0, this.colCnt),
                    isHorizontal: !0
                }), b = 0; e > b; b++) 
                    for (c = 0; f > c; c++) 
                        d.trigger("dayRender", null, this.getCellDate(b, c), this.getCellEl(b, c))
            },
            renderDayRowHtml      : function (a, b) {
                var c = this.view,
                    d = ["fc-row", "fc-week", c.widgetContentClass];
                return b && d.push("fc-rigid"),
                '<div class="' + d.join(" ") + '"><div class="fc-bg"><table>' + this.renderBgTrHtml(a) + '</table></div><div class="fc-content-skeleton"><table>' + (this.numbersVisible
                    ? "<thead>" + this.renderNumberTrHtml(a) + "</thead>"
                    : "") + "</table></div></div>"
            },
            renderDrag            : function (a, b) {
                return this.renderHighlight(this.eventToSpan(a)),
                b && !b
                    .el
                    .closest(this.el)
                    .length
                    ? this.renderEventLocationHelper(a, b)
                    : void 0
            },
            renderEventResize     : function (a, b) {
                return this.renderHighlight(this.eventToSpan(a)),
                this.renderEventLocationHelper(a, b)
            },
            renderFill            : function (b, c, d) {
                var e,
                    f,
                    g,
                    h = [];
                for (c = this.renderFillSegEls(b, c), e = 0; e < c.length; e++) 
                    f = c[e],
                    g = this.renderFillRow(b, f, d),
                    this
                        .rowEls
                        .eq(f.row)
                        .append(g),
                    h.push(g[0]);
                return this.elsByFill[b] = a(h),
                c
            },
            renderFillRow         : function (b, c, d) {
                var e,
                    f,
                    g = this.colCnt,
                    h = c.leftCol,
                    i = c.rightCol + 1;
                return d = d || b.toLowerCase(),
                e        = a('<div class="fc-' + d + '-skeleton"><table><tr/></table></div>'),
                f        = e.find("tr"),
                h > 0 && f.append('<td colspan="' + h + '"/>'),
                f.append(c.el.attr("colspan", i - h)),
                g > i && f.append('<td colspan="' + (g - i) + '"/>'),
                this.bookendCells(f),
                e
            },
            renderHelper          : function (b, c) {
                var d,
                    e = [],
                    f = this.eventToSegs(b);
                return f = this.renderFgSegEls(f),
                d        = this.renderSegRows(f),
                this
                    .rowEls
                    .each(function (b, f) {
                        var g,
                            h = a(f),
                            i = a('<div class="fc-helper-skeleton"><table/></div>');
                        g = c && c.row === b
                            ? c
                                .el
                                .position()
                                .top
                            : h
                                .find(".fc-content-skeleton tbody")
                                .position()
                                .top,
                        i
                            .css("top", g)
                            .find("table")
                            .append(d[b].tbodyEl),
                        h.append(i),
                        e.push(i[0])
                    }),
                this.helperEls = a(e)
            },
            renderNumberCellHtml  : function (a) {
                var b;
                return this.view.dayNumbersVisible
                    ? (b = this.getDayClasses(a), b.unshift("fc-day-number"), '<td class="' + b.join(" ") + '" data-date="' + a.format() + '">' + a.date() + "</td>")
                    : "<td/>"
            },
            renderNumberCellsHtml : function (a) {
                var b,
                    c,
                    d = [];
                for (b = 0; b < this.colCnt; b++) 
                    c = this.getCellDate(a, b),
                    d.push(this.renderNumberCellHtml(c));
                return d.join("")
            },
            renderNumberIntroHtml : function (a) {
                return this.renderIntroHtml()
            },
            renderNumberTrHtml    : function (a) {
                return "<tr>" + (this.isRTL
                    ? ""
                    : this.renderNumberIntroHtml(a)) + this.renderNumberCellsHtml(a) + (this.isRTL
                    ? this.renderNumberIntroHtml(a)
                    : "") + "</tr>"
            },
            rowCoordCache         : null,
            rowEls                : null,
            spanToSegs            : function (a) {
                var b,
                    c,
                    d = this.sliceRangeByRow(a);
                for (b = 0; b < d.length; b++) 
                    c = d[b],
                    this.isRTL
                        ? (c.leftCol = this.daysPerRow - 1 - c.lastRowDayIndex, c.rightCol = this.daysPerRow - 1 - c.firstRowDayIndex)
                        : (c.leftCol = c.firstRowDayIndex, c.rightCol = c.lastRowDayIndex);
                return d
            },
            unrenderBusinessHours : function () {
                this.unrenderFill("businessHours")
            },
            unrenderDates         : function () {
                this.removeSegPopover()
            },
            unrenderDrag          : function () {
                this.unrenderHighlight(),
                this.unrenderHelper()
            },
            unrenderEventResize   : function () {
                this.unrenderHighlight(),
                this.unrenderHelper()
            },
            unrenderHelper        : function () {
                this.helperEls && (this.helperEls.remove(), this.helperEls = null)
            }
        });
    vb.mixin({
        buildSegLevels: function (a) {
            var b,
                c,
                d,
                e = [];
            for (this.sortEventSegs(a), b = 0; b < a.length; b++) {
                for (c = a[b], d = 0; d < e.length && Ja(c, e[d]); d++) 
                ;
                c.level = d,
                (e[d] || (e[d] = [])).push(c)
            }
            for (d = 0; d < e.length; d++) 
                e[d].sort(Ka);
            return e
        },
        fgSegHtml     : function (a, b) {
            var c,
                d,
                e = this.view,
                f = a.event,
                g = e.isEventDraggable(f),
                h = !b && f.allDay && a.isStart && e.isEventResizableFromStart(f),
                i = !b && f.allDay && a.isEnd && e.isEventResizableFromEnd(f),
                j = this.getSegClasses(a, g, h || i),
                k = ea(this.getSegSkinCss(a)),
                l = "";
            return j.unshift("fc-day-grid-event", "fc-h-event"),
            a.isStart && (c = this.getEventTimeText(f), c && (l = '<span class="fc-time">' + ca(c) + "</span>")),
            d = '<span class="fc-title">' + (ca(f.title || "") || "&nbsp;") + "</span>",
            '<a class="' + j.join(" ") + '"' + (f.url
                ? ' href="' + ca(f.url) + '"'
                : "") + (k
                ? ' style="' + k + '"'
                : "") + '><div class="fc-content">' + (this.isRTL
                ? d + " " + l
                : l + " " + d) + "</div>" + (h
                ? '<div class="fc-resizer fc-start-resizer" />'
                : "") + (i
                ? '<div class="fc-resizer fc-end-resizer" />'
                : "") + "</a>"
        },
        getEventSegs  : function () {
            return tb
                .prototype
                .getEventSegs
                .call(this)
                .concat(this.popoverSegs || [])
        },
        groupSegRows  : function (a) {
            var b,
                c = [];
            for (b = 0; b < this.rowCnt; b++) 
                c.push([]);
            for (b = 0; b < a.length; b++) 
                c[a[b].row].push(a[b]);
            return c
        },
        renderBgSegs  : function (b) {
            var c = a.grep(b, function (a) {
                return a.event.allDay
            });
            return tb
                .prototype
                .renderBgSegs
                .call(this, c)
        },
        renderFgSegs  : function (b) {
            var c;
            return b = this.renderFgSegEls(b),
            c        = this.rowStructs = this.renderSegRows(b),
            this
                .rowEls
                .each(function (b, d) {
                    a(d)
                        .find(".fc-content-skeleton > table")
                        .append(c[b].tbodyEl)
                }),
            b
        },
        renderSegRow  : function (b, c) {
            function d(b) {
                for (; b > g;) 
                    k = (r[e - 1] || [])[g],
                    k
                        ? k.attr("rowspan", parseInt(k.attr("rowspan") || 1, 10) + 1)
                        : (k = a("<td/>"), h.append(k)),
                    q[e][g] = k,
                    r[e][g] = k,
                    g++
            }
            var e,
                f,
                g,
                h,
                i,
                j,
                k,
                l = this.colCnt,
                m = this.buildSegLevels(c),
                n = Math.max(1, m.length),
                o = a("<tbody/>"),
                p = [],
                q = [],
                r = [];
            for (e = 0; n > e; e++) {
                if (f = m[e], g = 0, h = a("<tr/>"), p.push([]), q.push([]), r.push([]), f) 
                    for (i = 0; i < f.length; i++) {
                        for (j = f[i], d(j.leftCol), k = a('<td class="fc-event-container"/>').append(j.el), j.leftCol != j.rightCol
                            ? k.attr("colspan", j.rightCol - j.leftCol + 1)
                            : r[e][g] = k; g <= j.rightCol;) 
                            q[e][g] = k,
                            p[e][g] = j,
                            g++;
                        h.append(k)
                    }
                d(l),
                this.bookendCells(h),
                o.append(h)
            }
            return {
                cellMatrix: q,
                row       : b,
                segLevels : m,
                segMatrix : p,
                segs      : c,
                tbodyEl   : o
            }
        },
        renderSegRows : function (a) {
            var b,
                c,
                d = [];
            for (b = this.groupSegRows(a), c = 0; c < b.length; c++) 
                d.push(this.renderSegRow(c, b[c]));
            return d
        },
        rowStructs    : null,
        unrenderEvents: function () {
            this.removeSegPopover(),
            tb
                .prototype
                .unrenderEvents
                .apply(this, arguments)
        },
        unrenderFgSegs: function () {
            for (var a, b = this.rowStructs || []; a = b.pop();) 
                a
                    .tbodyEl
                    .remove();
            this.rowStructs = null
        }
    }),
    vb.mixin({
        computeRowLevelLimit   : function (b) {
            function c(b, c) {
                f = Math.max(f, a(c).outerHeight())
            }
            var d,
                e,
                f,
                g = this
                    .rowEls
                    .eq(b),
                h = g.height(),
                i = this
                    .rowStructs[b]
                    .tbodyEl
                    .children();
            for (d = 0; d < i.length; d++) 
                if (e = i.eq(d).removeClass("fc-limited"), f = 0, e.find("> td > :first-child").each(c), e.position().top + f > h) 
                    return d;
        return !1
        },
        getCellSegs            : function (a, b, c) {
            for (var d, e = this.rowStructs[a].segMatrix, f = c || 0, g = []; f < e.length;) 
                d = e[f][b],
                d && g.push(d),
                f++;
            return g
        },
        getMoreLinkText        : function (a) {
            var b = this
                .view
                .opt("eventLimitText");
            return "function" == typeof b
                ? b(a)
                : "+" + a + " " + b
        },
        limitRow               : function (b, c) {
            function d(d) {
                for (; d > w;) 
                    j = t.getCellSegs(b, w, c),
                    j.length && (m = f[c - 1][w], s = t.renderMoreLink(b, w, j), r = a("<div/>").append(s), m.append(r), v.push(r[0])),
                    w++
            }
            var e,
                f,
                g,
                h,
                i,
                j,
                k,
                l,
                m,
                n,
                o,
                p,
                q,
                r,
                s,
                t = this,
                u = this.rowStructs[b],
                v = [],
                w = 0;
            if (c && c < u.segLevels.length) {
                for (e = u.segLevels[c - 1], f = u.cellMatrix, g = u.tbodyEl.children().slice(c).addClass("fc-limited").get(), h = 0; h < e.length; h++) {
                    for (i = e[h], d(i.leftCol), l = [], k = 0; w <= i.rightCol;) 
                        j = this.getCellSegs(b, w, c),
                        l.push(j),
                        k += j.length,
                        w++;
                    if (k) {
                        for (m = f[c - 1][i.leftCol], n = m.attr("rowspan") || 1, o = [], p = 0; p < l.length; p++) 
                            q = a('<td class="fc-more-cell"/>').attr("rowspan", n),
                            j = l[p],
                            s = this.renderMoreLink(b, i.leftCol + p, [i].concat(j)),
                            r = a("<div/>").append(s),
                            q.append(r),
                            o.push(q[0]),
                            v.push(q[0]);
                        m
                            .addClass("fc-limited")
                            .after(a(o)),
                        g.push(m[0])
                    }
                }
                d(this.colCnt),
                u.moreEls    = a(v),
                u.limitedEls = a(g)
            }
        },
        limitRows              : function (a) {
            var b,
                c,
                d = this.rowStructs || [];
            for (b = 0; b < d.length; b++) 
                this.unlimitRow(b),
                c = a
                    ? "number" == typeof a
                        ? a
                        : this.computeRowLevelLimit(b)
                    : !1,
                c !== !1 && this.limitRow(b, c)
        },
        popoverSegs            : null,
        removeSegPopover       : function () {
            this.segPopover && this
                .segPopover
                .hide()
        },
        renderMoreLink         : function (b, c, d) {
            var e = this,
                f = this.view;
            return a('<a class="fc-more"/>')
                .text(this.getMoreLinkText(d.length))
                .on("click", function (g) {
                    var h = f.opt("eventLimitClick"),
                        i = e.getCellDate(b, c),
                        j = a(this),
                        k = e.getCellEl(b, c),
                        l = e.getCellSegs(b, c),
                        m = e.resliceDaySegs(l, i),
                        n = e.resliceDaySegs(d, i);
                    "function" == typeof h && (h = f.trigger("eventLimitClick", null, {
                        date      : i,
                        dayEl     : k,
                        hiddenSegs: n,
                        moreEl    : j,
                        segs      : m
                    }, g)),
                    "popover" === h
                        ? e.showSegPopover(b, c, j, m)
                        : "string" == typeof h && f
                            .calendar
                            .zoomTo(i, h)
                })
        },
        renderSegPopoverContent: function (b, c, d) {
            var e,
                f = this.view,
                g = f.opt("theme"),
                h = this
                    .getCellDate(b, c)
                    .format(f.opt("dayPopoverFormat")),
                i = a('<div class="fc-header ' + f.widgetHeaderClass + '"><span class="fc-close ' + (g
                    ? "ui-icon ui-icon-closethick"
                    : "fc-icon fc-icon-x") + '"></span><span class="fc-title">' + ca(h) + '</span><div class="fc-clear"/></div><div class="fc-body ' + f.widgetContentClass + '"><div class="fc-event-container"></div></div>'),
                j = i.find(".fc-event-container");
            for (d = this.renderFgSegEls(d, !0), this.popoverSegs = d, e = 0; e < d.length; e++) 
                this.prepareHits(),
                d[e].hit = this.getCellHit(b, c),
                this.releaseHits(),
                j.append(d[e].el);
            return i
        },
        resliceDaySegs         : function (b, c) {
            var d = a.map(b, function (a) {
                    return a.event
                }),
                e = c.clone(),
                f = e
                    .clone()
                    .add(1, "days"),
                g = {
                    end  : f,
                    start: e
                };
            return b = this.eventsToSegs(d, function (a) {
                var b = K(a, g);
                return b
                    ? [b]
                    : []
            }),
            this.sortEventSegs(b),
            b
        },
        segPopover             : null,
        showSegPopover         : function (a, b, c, d) {
            var e,
                f,
                g = this,
                h = this.view,
                i = c.parent();
            e               = 1 == this.rowCnt
                ? h.el
                : this
                    .rowEls
                    .eq(a),
            f               = {
                autoHide         : !0,
                className        : "fc-more-popover",
                content          : this.renderSegPopoverContent(a, b, d),
                hide             : function () {
                    g
                        .segPopover
                        .removeElement(),
                    g.segPopover  = null,
                    g.popoverSegs = null
                },
                parentEl         : this.el,
                top              : e
                    .offset()
                    .top,
                viewportConstrain: h.opt("popoverViewportConstrain")
            },
            this.isRTL
                ? f.right   = i
                    .offset()
                    .left + i.outerWidth() + 1
                : f.left    = i
                    .offset()
                    .left - 1,
            this.segPopover = new ob(f),
            this
                .segPopover
                .show()
        },
        unlimitRow             : function (a) {
            var b = this.rowStructs[a];
            b.moreEls && (b.moreEls.remove(), b.moreEls = null),
            b.limitedEls && (b.limitedEls.removeClass("fc-limited"), b.limitedEls = null)
        }
    });
    var wb = Wa.TimeGrid = tb.extend(ub, {
        colCoordCache         : null,
        colEls                : null,
        computeDateTop        : function (a, c) {
            return this.computeTimeTop(b.duration(a - c.clone().stripTime()))
        },
        computeDisplayEventEnd: function () {
            return !0
        },
        computeEventTimeFormat: function () {
            return this
                .view
                .opt("noMeridiemTimeFormat")
        },
        computeLabelInterval  : function (a) {
            var c,
                d,
                e;
            for (c = Nb.length - 1; c >= 0; c--) 
                if (d = b.duration(Nb[c]), e = R(d, a), ha(e) && e > 1) 
                    return d;
        return b.duration(a)
        },
        computeSnapTime       : function (a) {
            return b.duration(this.minTime + this.snapDuration * a)
        },
        computeTimeTop        : function (a) {
            var b,
                c,
                d = this.slatEls.length,
                e = (a - this.minTime) / this.slotDuration;
            return e = Math.max(0, e),
            e        = Math.min(d, e),
            b        = Math.floor(e),
            b        = Math.min(b, d - 1),
            c        = e - b,
            this
                .slatCoordCache
                .getTopPosition(b) + this
                .slatCoordCache
                .getHeight(b) * c
        },
        constructor           : function () {
            tb.apply(this, arguments),
            this.processOptions()
        },
        getHitEl              : function (a) {
            return this
                .colEls
                .eq(a.col)
        },
        getHitSpan            : function (a) {
            var b,
                c = this.getCellDate(0, a.col),
                d = this.computeSnapTime(a.snap);
            return c.time(d),
            b = c
                .clone()
                .add(this.snapDuration), {
                end  : b,
                start: c
            }
        },
        getNowIndicatorUnit   : function () {
            return "minute"
        },
        getTotalSlatHeight    : function () {
            return this
                .slatContainerEl
                .outerHeight()
        },
        labelFormat           : null,
        labelInterval         : null,
        maxTime               : null,
        minTime               : null,
        nowIndicatorEls       : null,
        prepareHits           : function () {
            this
                .colCoordCache
                .build(),
            this
                .slatCoordCache
                .build()
        },
        processOptions        : function () {
            var c,
                d = this.view,
                e = d.opt("slotDuration"),
                f = d.opt("snapDuration");
            e                      = b.duration(e),
            f                      = f
                ? b.duration(f)
                : e,
            this.slotDuration      = e,
            this.snapDuration      = f,
            this.snapsPerSlot      = e / f,
            this.minResizeDuration = f,
            this.minTime           = b.duration(d.opt("minTime")),
            this.maxTime           = b.duration(d.opt("maxTime")),
            c                      = d.opt("slotLabelFormat"),
            a.isArray(c) && (c = c[c.length - 1]),
            this.labelFormat   = c || d.opt("axisFormat") || d.opt("smallTimeFormat"),
            c                  = d.opt("slotLabelInterval"),
            this.labelInterval = c
                ? b.duration(c)
                : this.computeLabelInterval(e)
        },
        queryHit              : function (a, b) {
            var c = this.snapsPerSlot,
                d = this.colCoordCache,
                e = this.slatCoordCache,
                f = d.getHorizontalIndex(a),
                g = e.getVerticalIndex(b);
            if (null != f && null != g) {
                var h = e.getTopOffset(g),
                    i = e.getHeight(g),
                    j = (b - h) / i,
                    k = Math.floor(j * c),
                    l = g * c + k,
                    m = h + k / c * i,
                    n = h + (k + 1) / c * i;
                return {
                    bottom   : n,
                    col      : f,
                    component: this,
                    left     : d.getLeftOffset(f),
                    right    : d.getRightOffset(f),
                    snap     : l,
                    top      : m
                }
            }
        },
        rangeUpdated          : function () {
            this.updateDayTable()
        },
        releaseHits           : function () {
            this
                .colCoordCache
                .clear()
        },
        renderBusinessHours   : function () {
            var a = this
                    .view
                    .calendar
                    .getBusinessHoursEvents(),
                b = this.eventsToSegs(a);
            this.renderBusinessSegs(b)
        },
        renderDates           : function () {
            this
                .el
                .html(this.renderHtml()),
            this.colEls          = this
                .el
                .find(".fc-day"),
            this.slatContainerEl = this
                .el
                .find(".fc-slats"),
            this.slatEls         = this
                .slatContainerEl
                .find("tr"),
            this.colCoordCache   = new pb({
                els         : this.colEls,
                isHorizontal: !0
            }),
            this.slatCoordCache  = new pb({
                els       : this.slatEls,
                isVertical: !0
            }),
            this.renderContentSkeleton()
        },
        renderDrag            : function (a, b) {
            return b
                ? this.renderEventLocationHelper(a, b)
                : void this.renderHighlight(this.eventToSpan(a))
        },
        renderEventResize     : function (a, b) {
            return this.renderEventLocationHelper(a, b)
        },
        renderHelper          : function (a, b) {
            return this.renderHelperSegs(this.eventToSegs(a), b)
        },
        renderHighlight       : function (a) {
            this.renderHighlightSegs(this.spanToSegs(a))
        },
        renderHtml            : function () {
            return '<div class="fc-bg"><table>' + this.renderBgTrHtml(0) + '</table></div><div class="fc-slats"><table>' + this.renderSlatRowHtml() + "</table></div>"
        },
        renderNowIndicator    : function (b) {
            var c,
                d = this.spanToSegs({end: b, start: b}),
                e = this.computeDateTop(b, b),
                f = [];
            for (c = 0; c < d.length; c++) 
                f.push(a('<div class="fc-now-indicator fc-now-indicator-line"></div>').css("top", e).appendTo(this.colContainerEls.eq(d[c].col))[0]);
            d.length > 0 && f.push(a('<div class="fc-now-indicator fc-now-indicator-arrow"></div>').css("top", e).appendTo(this.el.find(".fc-content-skeleton"))[0]),
            this.nowIndicatorEls = a(f)
        },
        renderSelection       : function (a) {
            this
                .view
                .opt("selectHelper")
                ? this.renderEventLocationHelper(a)
                : this.renderHighlight(a)
        },
        renderSlatRowHtml     : function () {
            for (var a, c, d, e = this.view, f = this.isRTL, g = "", h = b.duration(+ this.minTime); h < this.maxTime;) 
                a = this
                    .start
                    .clone()
                    .time(h),
                c = ha(R(h, this.labelInterval)),
                d = '<td class="fc-axis fc-time ' + e.widgetContentClass + '" ' + e.axisStyleAttr() + ">" + (c
                    ? "<span>" + ca(a.format(this.labelFormat)) + "</span>"
                    : "") + "</td>",
                g += '<tr data-time="' + a.format("HH:mm:ss") + '"' + (c
                    ? ""
                    : ' class="fc-minor"') + ">" + (f
                    ? ""
                    : d) + '<td class="' + e.widgetContentClass + '"/>' + (f
                    ? d
                    : "") + "</tr>",
                h.add(this.slotDuration);
            return g
        },
        slatContainerEl       : null,
        slatCoordCache        : null,
        slatEls               : null,
        sliceRangeByTimes     : function (a) {
            var b,
                c,
                d,
                e,
                f = [];
            for (c = 0; c < this.daysPerRow; c++) 
                d = this
                    .dayDates[c]
                    .clone(),
                e = {
                    end  : d
                        .clone()
                        .time(this.maxTime),
                    start: d
                        .clone()
                        .time(this.minTime)
                }
            ,
            b = K(a, e),
            b && (b.dayIndex = c, f.push(b));
            return f
        },
        slotDuration          : null,
        snapDuration          : null,
        snapsPerSlot          : null,
        spanToSegs            : function (a) {
            var b,
                c = this.sliceRangeByTimes(a);
            for (b = 0; b < c.length; b++) 
                this.isRTL
                    ? c[b].col = this.daysPerRow - 1 - c[b].dayIndex
                    : c[b].col = c[b].dayIndex;
            return c
        },
        unrenderBusinessHours : function () {
            this.unrenderBusinessSegs()
        },
        unrenderDrag          : function () {
            this.unrenderHelper(),
            this.unrenderHighlight()
        },
        unrenderEventResize   : function () {
            this.unrenderHelper()
        },
        unrenderHelper        : function () {
            this.unrenderHelperSegs()
        },
        unrenderHighlight     : function () {
            this.unrenderHighlightSegs()
        },
        unrenderNowIndicator  : function () {
            this.nowIndicatorEls && (this.nowIndicatorEls.remove(), this.nowIndicatorEls = null)
        },
        unrenderSelection     : function () {
            this.unrenderHelper(),
            this.unrenderHighlight()
        },
        updateSize            : function (a) {
            this
                .slatCoordCache
                .build(),
            a && this.updateSegVerticals([].concat(this.fgSegs || [], this.bgSegs || [], this.businessSegs || []))
        }
    });
    wb.mixin({
        assignFgSegHorizontals    : function (a) {
            var b,
                c;
            for (b = 0; b < a.length; b++) 
                c = a[b],
                c
                    .el
                    .css(this.generateFgSegHorizontalCss(c)),
                c.bottom - c.top < 30 && c
                    .el
                    .addClass("fc-short")
            },
        assignSegVerticals        : function (a) {
            var b,
                c;
            for (b = 0; b < a.length; b++) 
                c = a[b],
                c
                    .el
                    .css(this.generateSegVerticalCss(c))
            },
        attachSegsByCol           : function (a, b) {
            var c,
                d,
                e;
            for (c = 0; c < this.colCnt; c++) 
                for (d = a[c], e = 0; e < d.length; e++) 
                    b
                        .eq(c)
                        .append(d[e].el)
            },
        bgContainerEls            : null,
        bgSegs                    : null,
        businessContainerEls      : null,
        businessSegs              : null,
        colContainerEls           : null,
        compareForwardSegs        : function (a, b) {
            return b.forwardPressure - a.forwardPressure || (a.backwardCoord || 0) - (b.backwardCoord || 0) || this.compareEventSegs(a, b)
        },
        computeFgSegForwardBack   : function (a, b, c) {
            var d,
                e = a.forwardSegs;
            if (void 0 === a.forwardCoord) 
                for (e.length
                    ? (this.sortForwardSegs(e), this.computeFgSegForwardBack(e[0], b + 1, c), a.forwardCoord = e[0].backwardCoord)
                    : a.forwardCoord = 1, a.backwardCoord = a.forwardCoord - (a.forwardCoord - c) / (b + 1), d = 0; d < e.length; d++) 
                    this.computeFgSegForwardBack(e[d], 0, a.forwardCoord)
        },
        computeFgSegHorizontals   : function (a) {
            var b,
                c,
                d;
            if (this.sortEventSegs(a), b = La(a), Ma(b), c = b[0]) {
                for (d = 0; d < c.length; d++) 
                    Na(c[d]);
                for (d = 0; d < c.length; d++) 
                    this.computeFgSegForwardBack(c[d], 0, 0)
            }
        },
        computeSegVerticals       : function (a) {
            var b,
                c;
            for (b = 0; b < a.length; b++) 
                c        = a[b],
                c.top    = this.computeDateTop(c.start, c.start),
                c.bottom = this.computeDateTop(c.end, c.start)
        },
        fgContainerEls            : null,
        fgSegHtml                 : function (a, b) {
            var c,
                d,
                e,
                f = this.view,
                g = a.event,
                h = f.isEventDraggable(g),
                i = !b && a.isStart && f.isEventResizableFromStart(g),
                j = !b && a.isEnd && f.isEventResizableFromEnd(g),
                k = this.getSegClasses(a, h, i || j),
                l = ea(this.getSegSkinCss(a));
            return k.unshift("fc-time-grid-event", "fc-v-event"),
            f.isMultiDayEvent(g)
                ? (a.isStart || a.isEnd) && (c = this.getEventTimeText(a), d = this.getEventTimeText(a, "LT"), e = this.getEventTimeText(a, null, !1))
                : (c = this.getEventTimeText(g), d = this.getEventTimeText(g, "LT"), e = this.getEventTimeText(g, null, !1)),
            '<a class="' + k.join(" ") + '"' + (g.url
                ? ' href="' + ca(g.url) + '"'
                : "") + (l
                ? ' style="' + l + '"'
                : "") + '><div class="fc-content">' + (c
                ? '<div class="fc-time" data-start="' + ca(e) + '" data-full="' + ca(d) + '"><span>' + ca(c) + "</span></div>"
                : "") + (g.title
                ? '<div class="fc-title">' + ca(g.title) + "</div>"
                : "") + '</div><div class="fc-bg"/>' + (j
                ? '<div class="fc-resizer fc-end-resizer" />'
                : "") + "</a>"
        },
        fgSegs                    : null,
        generateFgSegHorizontalCss: function (a) {
            var b,
                c,
                d = this
                    .view
                    .opt("slotEventOverlap"),
                e = a.backwardCoord,
                f = a.forwardCoord,
                g = this.generateSegVerticalCss(a);
            return d && (f = Math.min(1, e + 2 * (f - e))),
            this.isRTL
                ? (b = 1 - f, c = e)
                : (b = e, c = 1 - f),
            g.zIndex = a.level + 1,
            g.left   = 100 * b + "%",
            g.right  = 100 * c + "%",
            d && a.forwardPressure && (g[this.isRTL
                    ? "marginLeft"
                    : "marginRight"] = 20),
            g
        },
        generateSegVerticalCss    : function (a) {
            return {
                bottom: -a.bottom,
                top   : a.top
            }
        },
        groupSegsByCol            : function (a) {
            var b,
                c = [];
            for (b = 0; b < this.colCnt; b++) 
                c.push([]);
            for (b = 0; b < a.length; b++) 
                c[a[b].col].push(a[b]);
            return c
        },
        helperContainerEls        : null,
        helperSegs                : null,
        highlightContainerEls     : null,
        highlightSegs             : null,
        renderBgSegs              : function (a) {
            return a = this.renderFillSegEls("bgEvent", a),
            this.updateSegVerticals(a),
            this.attachSegsByCol(this.groupSegsByCol(a), this.bgContainerEls),
            this.bgSegs = a,
            a
        },
        renderBusinessSegs        : function (a) {
            a = this.renderFillSegEls("businessHours", a),
            this.updateSegVerticals(a),
            this.attachSegsByCol(this.groupSegsByCol(a), this.businessContainerEls),
            this.businessSegs = a
        },
        renderContentSkeleton     : function () {
            var b,
                c,
                d = "";
            for (b = 0; b < this.colCnt; b++) 
                d += '<td><div class="fc-content-col"><div class="fc-event-container fc-helper-contain' +
                        'er"></div><div class="fc-event-container"></div><div class="fc-highlight-contain' +
                        'er"></div><div class="fc-bgevent-container"></div><div class="fc-business-contai' +
                        'ner"></div></div></td>';
            c                          = a('<div class="fc-content-skeleton"><table><tr>' + d + "</tr></table></div>"),
            this.colContainerEls       = c.find(".fc-content-col"),
            this.helperContainerEls    = c.find(".fc-helper-container"),
            this.fgContainerEls        = c.find(".fc-event-container:not(.fc-helper-container)"),
            this.bgContainerEls        = c.find(".fc-bgevent-container"),
            this.highlightContainerEls = c.find(".fc-highlight-container"),
            this.businessContainerEls  = c.find(".fc-business-container"),
            this.bookendCells(c.find("tr")),
            this
                .el
                .append(c)
        },
        renderFgSegs              : function (a) {
            return a    = this.renderFgSegsIntoContainers(a, this.fgContainerEls),
            this.fgSegs = a,
            a
        },
        renderFgSegsIntoContainers: function (a, b) {
            var c,
                d;
            for (a = this.renderFgSegEls(a), c = this.groupSegsByCol(a), d = 0; d < this.colCnt; d++) 
                this.updateFgSegCoords(c[d]);
            return this.attachSegsByCol(c, b),
            a
        },
        renderHelperSegs          : function (b, c) {
            var d,
                e,
                f,
                g = [];
            for (b = this.renderFgSegsIntoContainers(b, this.helperContainerEls), d = 0; d < b.length; d++) 
                e = b[d],
                c && c.col === e.col && (f = c.el, e.el.css({
                    "margin-left" : f.css("margin-left"),
                    "margin-right": f.css("margin-right"),
                    left          : f.css("left"),
                    right         : f.css("right")
                })),
                g.push(e.el[0]);
            return this.helperSegs = b,
            a(g)
        },
        renderHighlightSegs       : function (a) {
            a = this.renderFillSegEls("highlight", a),
            this.updateSegVerticals(a),
            this.attachSegsByCol(this.groupSegsByCol(a), this.highlightContainerEls),
            this.highlightSegs = a
        },
        sortForwardSegs           : function (a) {
            a.sort(ia(this, "compareForwardSegs"))
        },
        unrenderBgSegs            : function () {
            this.unrenderNamedSegs("bgSegs")
        },
        unrenderBusinessSegs      : function () {
            this.unrenderNamedSegs("businessSegs")
        },
        unrenderFgSegs            : function () {
            this.unrenderNamedSegs("fgSegs")
        },
        unrenderHelperSegs        : function () {
            this.unrenderNamedSegs("helperSegs")
        },
        unrenderHighlightSegs     : function () {
            this.unrenderNamedSegs("highlightSegs")
        },
        unrenderNamedSegs         : function (a) {
            var b,
                c = this[a];
            if (c) {
                for (b = 0; b < c.length; b++) 
                    c[b]
                        .el
                        .remove();
                this[a] = null
            }
        },
        updateFgSegCoords         : function (a) {
            this.computeSegVerticals(a),
            this.computeFgSegHorizontals(a),
            this.assignSegVerticals(a),
            this.assignFgSegHorizontals(a)
        },
        updateSegVerticals        : function (a) {
            this.computeSegVerticals(a),
            this.assignSegVerticals(a)
        }
    });
    var xb = Wa.View = ya.extend(lb, mb, {
            bindGlobalHandlers       : function () {
                this.listenTo(a(document), "mousedown", this.handleDocumentMousedown),
                this.listenTo(a(document), "touchstart", this.processUnselect)
            },
            calendar                 : null,
            clear                    : function () {
                var b = this,
                    c = this.displaying;
                return c
                    ? ka(c, function () {
                        return b.displaying = null,
                        b.clearEvents(),
                        b.clearView()
                    })
                    : a.when()
            },
            clearEvents              : function () {
                var a;
                this.isEventsRendered && (a = this.queryScroll(), this.triggerEventUnrender(), this.destroyEvents && this.destroyEvents(), this.unrenderEvents(), this.setScroll(a), this.isEventsRendered = !1)
            },
            clearView                : function () {
                this.unselect(),
                this.stopNowIndicator(),
                this.triggerUnrender(),
                this.unrenderBusinessHours(),
                this.unrenderDates(),
                this.destroy && this.destroy()
            },
            computeDayRange          : function (a) {
                var b,
                    c = a
                        .start
                        .clone()
                        .stripTime(),
                    d = a.end,
                    e = null;
                return d && (e = d.clone().stripTime(), b =+ d.time(), b && b >= this.nextDayThreshold && e.add(1, "days")),
                (!d || c >= e) && (e = c.clone().add(1, "days")), {
                    end  : e,
                    start: c
                }
            },
            computeInitialScroll     : function (a) {
                return 0
            },
            computeNextDate          : function (a) {
                return this.massageCurrentDate(a.clone().startOf(this.intervalUnit).add(this.intervalDuration))
            },
            computePrevDate          : function (a) {
                return this.massageCurrentDate(a.clone().startOf(this.intervalUnit).subtract(this.intervalDuration), -1)
            },
            computeRange             : function (a) {
                var b,
                    c,
                    d = O(this.intervalDuration),
                    e = a
                        .clone()
                        .startOf(d),
                    f = e
                        .clone()
                        .add(this.intervalDuration);
                return /year|month|week|day/.test(d)
                    ? (e.stripTime(), f.stripTime())
                    : (e.hasTime() || (e = this.calendar.time(0)), f.hasTime() || (f = this.calendar.time(0))),
                b = e.clone(),
                b = this.skipHiddenDays(b),
                c = f.clone(),
                c = this.skipHiddenDays(c, -1, !0), {
                    end          : c,
                    intervalEnd  : f,
                    intervalStart: e,
                    intervalUnit : d,
                    start        : b
                }
            },
            computeTitle             : function () {
                return this.formatRange({
                    end  : this
                        .calendar
                        .applyTimezone(this.intervalEnd),
                    start: this
                        .calendar
                        .applyTimezone(this.intervalStart)
                }, this.opt("titleFormat") || this.computeTitleFormat(), this.opt("titleRangeSeparator"))
            },
            computeTitleFormat       : function () {
                return "year" == this.intervalUnit
                    ? "YYYY"
                    : "month" == this.intervalUnit
                        ? this.opt("monthYearFormat")
                        : this
                            .intervalDuration
                            .as("days") > 1
                            ? "ll"
                            : "LL"
            },
            constructor              : function (a, c, d, e) {
                this.calendar         = a,
                this.type             = this.name = c,
                this.options          = d,
                this.intervalDuration = e || b.duration(1, "day"),
                this.nextDayThreshold = b.duration(this.opt("nextDayThreshold")),
                this.initThemingProps(),
                this.initHiddenDays(),
                this.isRTL           = this.opt("isRTL"),
                this.eventOrderSpecs = G(this.opt("eventOrder")),
                this.initialize()
            },
            display                  : function (a, b) {
                var c = this,
                    d = null;
                return null != b && this.displaying && (d = this.queryScroll()),
                this
                    .calendar
                    .freezeContentHeight(),
                ka(this.clear(), function () {
                    return c.displaying = ka(c.displayView(a), function () {
                        null != b
                            ? c.setScroll(b)
                            : c.forceScroll(c.computeInitialScroll(d)),
                        c
                            .calendar
                            .unfreezeContentHeight(),
                        c.triggerRender()
                    })
                })
            },
            displayEvents            : function (a) {
                var b = this.queryScroll();
                this.clearEvents(),
                this.renderEvents(a),
                this.isEventsRendered = !0,
                this.setScroll(b),
                this.triggerEventRender()
            },
            displaying               : null,
            displayView              : function (a) {
                this.isSkeletonRendered || (this.renderSkeleton(), this.isSkeletonRendered = !0),
                a && this.setDate(a),
                this.render && this.render(),
                this.renderDates(),
                this.updateSize(),
                this.renderBusinessHours(),
                this.startNowIndicator()
            },
            el                       : null,
            end                      : null,
            eventOrderSpecs          : null,
            forceScroll              : function (a) {
                var b = this;
                this.setScroll(a),
                setTimeout(function () {
                    b.setScroll(a)
                }, 0)
            },
            formatRange              : function (a, b, c) {
                var d = a.end;
                return d.hasTime() || (d = d.clone().subtract(1)),
                ta(a.start, d, b, c, this.opt("isRTL"))
            },
            getEventSegs             : function () {
                return []
            },
            getNowIndicatorUnit      : function () {},
            handleDocumentMousedown  : function (a) {
                u(a) && this.processUnselect(a)
            },
            hideEvent                : function (a) {
                this
                    .renderedEventSegEach(function (a) {
                        a
                            .el
                            .css("visibility", "hidden")
                    }, a)
            },
            highlightStateClass      : null,
            initHiddenDays           : function () {
                var b,
                    c = this.opt("hiddenDays") || [],
                    d = [],
                    e = 0;
                for (this.opt("weekends") === !1 && c.push(0, 6), b = 0; 7 > b; b++) 
                    (d[b] = -1 !== a.inArray(b, c)) || e++;
                if (!e) 
                    throw "invalid hiddenDays";
                this.isHiddenDayHash = d
            },
            initialize               : function () {},
            initialNowDate           : null,
            initialNowQueriedMs      : null,
            initThemingProps         : function () {
                var a = this.opt("theme")
                    ? "ui"
                    : "fc";
                this.widgetHeaderClass   = a + "-widget-header",
                this.widgetContentClass  = a + "-widget-content",
                this.highlightStateClass = a + "-state-highlight"
            },
            intervalDuration         : null,
            intervalEnd              : null,
            intervalStart            : null,
            intervalUnit             : null,
            isEventDraggable         : function (a) {
                var b = a.source || {};
                return ba(a.startEditable, b.startEditable, this.opt("eventStartEditable"), a.editable, b.editable, this.opt("editable"))
            },
            isEventResizable         : function (a) {
                var b = a.source || {};
                return ba(a.durationEditable, b.durationEditable, this.opt("eventDurationEditable"), a.editable, b.editable, this.opt("editable"))
            },
            isEventResizableFromEnd  : function (a) {
                return this.isEventResizable(a)
            },
            isEventResizableFromStart: function (a) {
                return this.opt("eventResizableFromStart") && this.isEventResizable(a)
            },
            isEventSelected          : function (a) {
                return this.selectedEvent && this.selectedEvent._id === a._id
            },
            isEventsRendered         : !1,
            isHiddenDay              : function (a) {
                return b.isMoment(a) && (a = a.day()),
                this.isHiddenDayHash[a]
            },
            isHiddenDayHash          : null,
            isMultiDayEvent          : function (a) {
                var b = this.computeDayRange(a);
                return b
                    .end
                    .diff(b.start, "days") > 1
            },
            isNowIndicatorRendered   : null,
            isRTL                    : !1,
            isSelected               : !1,
            isSkeletonRendered       : !1,
            massageCurrentDate       : function (a, b) {
                return this
                    .intervalDuration
                    .as("days") <= 1 && this.isHiddenDay(a) && (a = this.skipHiddenDays(a, b), a.startOf("day")),
                a
            },
            name                     : null,
            nextDayThreshold         : null,
            nowIndicatorIntervalID   : null,
            nowIndicatorTimeoutID    : null,
            opt                      : function (a) {
                return this.options[a]
            },
            options                  : null,
            processEventUnselect     : function (b) {
                this.selectedEvent && (a(b.target).closest(".fc-selected").length || this.unselectEvent())
            },
            processRangeUnselect     : function (b) {
                var c;
                this.isSelected && this.opt("unselectAuto") && (c = this.opt("unselectCancel"), c && a(b.target).closest(c).length || this.unselect(b))
            },
            processUnselect          : function (a) {
                this.processRangeUnselect(a),
                this.processEventUnselect(a)
            },
            queryScroll              : function () {},
            removeElement            : function () {
                this.clear(),
                this.isSkeletonRendered && (this.unrenderSkeleton(), this.isSkeletonRendered = !1),
                this.unbindGlobalHandlers(),
                this
                    .el
                    .remove()
            },
            renderBusinessHours      : function () {},
            renderDates              : function () {},
            renderDrag               : function (a, b) {},
            renderedEventSegEach     : function (a, b) {
                var c,
                    d = this.getEventSegs();
                for (c = 0; c < d.length; c++) 
                    b && d[c].event._id !== b._id || d[c].el && a.call(this, d[c])
            },
            renderEvents             : function (a) {},
            renderNowIndicator       : function (a) {},
            renderSelection          : function (a) {},
            renderSkeleton           : function () {},
            reportEventDrop          : function (a, b, c, d, e) {
                var f = this.calendar,
                    g = f.mutateEvent(a, b, c),
                    h = function () {
                        g.undo(),
                        f.reportEventChange()
                    };
                this.triggerEventDrop(a, g.dateDelta, h, d, e),
                f.reportEventChange()
            },
            reportEventResize        : function (a, b, c, d, e) {
                var f = this.calendar,
                    g = f.mutateEvent(a, b, c),
                    h = function () {
                        g.undo(),
                        f.reportEventChange()
                    };
                this.triggerEventResize(a, g.durationDelta, h, d, e),
                f.reportEventChange()
            },
            reportExternalDrop       : function (b, c, d, e, f) {
                var g,
                    h,
                    i = b.eventProps;
                i && (g = a.extend({}, i, c), h = this.calendar.renderEvent(g, b.stick)[0]),
                this.triggerExternalDrop(h, c, d, e, f)
            },
            reportSelection          : function (a, b) {
                this.isSelected = !0,
                this.triggerSelect(a, b)
            },
            resolveEventEl           : function (b, c) {
                var d = this.trigger("eventRender", b, b, c);
                return d === !1
                    ? c = null
                    : d && d !== !0 && (c = a(d)),
                c
            },
            select                   : function (a, b) {
                this.unselect(b),
                this.renderSelection(a),
                this.reportSelection(a, b)
            },
            selectedEvent            : null,
            selectEvent              : function (a) {
                this.selectedEvent && this.selectedEvent === a || (this.unselectEvent(), this.renderedEventSegEach(function (a) {
                    a
                        .el
                        .addClass("fc-selected")
                }, a), this.selectedEvent = a)
            },
            setDate                  : function (a) {
                this.setRange(this.computeRange(a))
            },
            setElement               : function (a) {
                this.el = a,
                this.bindGlobalHandlers()
            },
            setHeight                : function (a, b) {},
            setRange                 : function (b) {
                a.extend(this, b),
                this.updateTitle()
            },
            setScroll                : function (a) {},
            showEvent                : function (a) {
                this
                    .renderedEventSegEach(function (a) {
                        a
                            .el
                            .css("visibility", "")
                    }, a)
            },
            skipHiddenDays           : function (a, b, c) {
                var d = a.clone();
                for (b = b || 1; this.isHiddenDayHash[(d.day() + (c
                        ? b
                        : 0) + 7) % 7];) 
                    d.add(b, "days");
                return d
            },
            start                    : null,
            startNowIndicator        : function () {
                var a,
                    c,
                    d,
                    e = this;
                this.opt("nowIndicator") && (a = this.getNowIndicatorUnit(), a && (c = ia(this, "updateNowIndicator"), this.initialNowDate = this.calendar.getNow(), this.initialNowQueriedMs =+ new Date, this.renderNowIndicator(this.initialNowDate), this.isNowIndicatorRendered = !0, d = this.initialNowDate.clone().startOf(a).add(1, a) - this.initialNowDate, this.nowIndicatorTimeoutID = setTimeout(function () {
                    e.nowIndicatorTimeoutID = null,
                    c(),
                    d                        =+ b.duration(1, a),
                    d                        = Math.max(100, d),
                    e.nowIndicatorIntervalID = setInterval(c, d)
                }, d)))
            },
            stopNowIndicator         : function () {
                this.isNowIndicatorRendered && (this.nowIndicatorTimeoutID && (clearTimeout(this.nowIndicatorTimeoutID), this.nowIndicatorTimeoutID = null), this.nowIndicatorIntervalID && (clearTimeout(this.nowIndicatorIntervalID), this.nowIndicatorIntervalID = null), this.unrenderNowIndicator(), this.isNowIndicatorRendered = !1)
            },
            title                    : null,
            trigger                  : function (a, b) {
                var c = this.calendar;
                return c
                    .trigger
                    .apply(c, [
                        a, b || this
                    ].concat(Array.prototype.slice.call(arguments, 2), [this]))
            },
            triggerDayClick          : function (a, b, c) {
                this.trigger("dayClick", b, this.calendar.applyTimezone(a.start), c)
            },
            triggerEventDrop         : function (a, b, c, d, e) {
                this.trigger("eventDrop", d[0], a, b, c, e, {})
            },
            triggerEventRender       : function () {
                this
                    .renderedEventSegEach(function (a) {
                        this.trigger("eventAfterRender", a.event, a.event, a.el)
                    }),
                this.trigger("eventAfterAllRender")
            },
            triggerEventResize       : function (a, b, c, d, e) {
                this.trigger("eventResize", d[0], a, b, c, e, {})
            },
            triggerEventUnrender     : function () {
                this
                    .renderedEventSegEach(function (a) {
                        this.trigger("eventDestroy", a.event, a.event, a.el)
                    })
            },
            triggerExternalDrop      : function (a, b, c, d, e) {
                this.trigger("drop", c[0], b.start, d, e),
                a && this.trigger("eventReceive", null, a)
            },
            triggerRender            : function () {
                this.trigger("viewRender", this, this, this.el)
            },
            triggerSelect            : function (a, b) {
                this.trigger("select", null, this.calendar.applyTimezone(a.start), this.calendar.applyTimezone(a.end), b)
            },
            triggerUnrender          : function () {
                this.trigger("viewDestroy", this, this, this.el)
            },
            type                     : null,
            unbindGlobalHandlers     : function () {
                this.stopListeningTo(a(document))
            },
            unrenderBusinessHours    : function () {},
            unrenderDates            : function () {},
            unrenderDrag             : function () {},
            unrenderEvents           : function () {},
            unrenderNowIndicator     : function () {},
            unrenderSelection        : function () {},
            unrenderSkeleton         : function () {},
            unselect                 : function (a) {
                this.isSelected && (this.isSelected = !1, this.destroySelection && this.destroySelection(), this.unrenderSelection(), this.trigger("unselect", null, a))
            },
            unselectEvent            : function () {
                this.selectedEvent && (this.renderedEventSegEach(function (a) {
                    a
                        .el
                        .removeClass("fc-selected")
                }, this.selectedEvent), this.selectedEvent = null)
            },
            updateHeight             : function (a) {
                var b = this.calendar;
                this.setHeight(b.getSuggestedViewHeight(), b.isHeightAuto())
            },
            updateNowIndicator       : function () {
                this.isNowIndicatorRendered && (this.unrenderNowIndicator(), this.renderNowIndicator(this.initialNowDate.clone().add(new Date - this.initialNowQueriedMs)))
            },
            updateSize               : function (a) {
                var b;
                a && (b = this.queryScroll()),
                this.updateHeight(a),
                this.updateWidth(a),
                this.updateNowIndicator(),
                a && this.setScroll(b)
            },
            updateTitle              : function () {
                this.title = this.computeTitle()
            },
            updateWidth              : function (a) {},
            widgetContentClass       : null,
            widgetHeaderClass        : null
        }),
        yb = Wa.Scroller = ya.extend({
            applyOverflow     : function () {
                this
                    .scrollEl
                    .css({"overflow-x": this.overflowX, "overflow-y": this.overflowY})
            },
            clear             : function () {
                this.setHeight("auto"),
                this.applyOverflow()
            },
            constructor       : function (a) {
                a              = a || {},
                this.overflowX = a.overflowX || a.overflow || "auto",
                this.overflowY = a.overflowY || a.overflow || "auto"
            },
            destroy           : function () {
                this
                    .el
                    .remove()
            },
            el                : null,
            getClientHeight   : function () {
                return this.scrollEl[0].clientHeight
            },
            getClientWidth    : function () {
                return this.scrollEl[0].clientWidth
            },
            getScrollbarWidths: function () {
                return q(this.scrollEl)
            },
            getScrollTop      : function () {
                return this
                    .scrollEl
                    .scrollTop()
            },
            lockOverflow      : function (a) {
                var b = this.overflowX,
                    c = this.overflowY;
                a = a || this.getScrollbarWidths(),
                "auto" === b && (b = a.top || a.bottom || this.scrollEl[0].scrollWidth - 1 > this.scrollEl[0].clientWidth
                    ? "scroll"
                    : "hidden"),
                "auto" === c && (c = a.left || a.right || this.scrollEl[0].scrollHeight - 1 > this.scrollEl[0].clientHeight
                    ? "scroll"
                    : "hidden"),
                this
                    .scrollEl
                    .css({"overflow-x": b, "overflow-y": c})
            },
            overflowX         : null,
            overflowY         : null,
            render            : function () {
                this.el = this.renderEl(),
                this.applyOverflow()
            },
            renderEl          : function () {
                return this.scrollEl = a('<div class="fc-scroller"></div>')
            },
            scrollEl          : null,
            setHeight         : function (a) {
                this
                    .scrollEl
                    .height(a)
            },
            setScrollTop      : function (a) {
                this
                    .scrollEl
                    .scrollTop(a)
            }
        }),
        zb = Wa.Calendar = ya.extend({
            buildSelectSpan        : function (a, b) {
                var c,
                    d = this
                        .moment(a)
                        .stripZone();
                return c = b
                    ? this
                        .moment(b)
                        .stripZone()
                    : d.hasTime()
                        ? d
                            .clone()
                            .add(this.defaultTimedEventDuration)
                        : d
                            .clone()
                            .add(this.defaultAllDayEventDuration), {
                    end  : c,
                    start: d
                }
            },
            buildViewSpec          : function (a) {
                for (var d, e, f, g, h = this.overrides.views || {}, i = [], j = [], k = [], l = a; l;) 
                    d = Xa[l],
                    e = h[l],
                    l = null,
                    "function" == typeof d && (d = {
                        "class": d
                    }),
                    d && (i.unshift(d), j.unshift(d.defaults || {}), f = f || d.duration, l = l || d.type),
                    e && (k.unshift(e), f = f || e.duration, l = l || e.type);
                return d = W(i),
                d.type   = a,
                d["class"]
                    ? (f && (f = b.duration(f), f.valueOf() && (d.duration = f, g = O(f), 1 === f.as(g) && (d.singleUnit = g, k.unshift(h[g] || {})))), d.defaults = c(j), d.overrides = c(k), this.buildViewSpecOptions(d), this.buildViewSpecButtonText(d, a), d)
                    : !1
            },
            buildViewSpecButtonText: function (a, b) {
                function c(c) {
                    var d = c.buttonText || {};
                    return d[b] || (a.singleUnit
                        ? d[a.singleUnit]
                        : null)
                }
                a.buttonTextOverride = c(this.dynamicOverrides) || c(this.overrides) || a.overrides.buttonText,
                a.buttonTextDefault  = c(this.langDefaults) || c(this.dirDefaults) || a.defaults.buttonText || c(zb.defaults) || (a.duration
                    ? this.humanizeDuration(a.duration)
                    : null) || b
            },
            buildViewSpecOptions   : function (a) {
                a.options = c([
                    zb.defaults,
                    a.defaults,
                    this.dirDefaults,
                    this.langDefaults,
                    this.overrides,
                    a.overrides,
                    this.dynamicOverrides
                ]),
                Ra(a.options)
            },
            constructor            : Qa,
            dirDefaults            : null,
            dynamicOverrides       : null,
            getUnitViewSpec        : function (b) {
                var c,
                    d,
                    e;
                if (-1 != a.inArray(b, _a)) 
                    for (c = this.header.getViewsWithButtons(), a.each(Wa.views, function (a) {
                        c.push(a)
                    }), d = 0; d < c.length; d++) 
                        if (e = this.getViewSpec(c[d]), e && e.singleUnit == b) 
                            return e
            },
            getViewSpec            : function (a) {
                var b = this.viewSpecCache;
                return b[a] || (b[a] = this.buildViewSpec(a))
            },
            header                 : null,
            initialize             : function () {},
            instantiateView        : function (a) {
                var b = this.getViewSpec(a);
                return new b["class"](this, a, b.options, b.duration)
            },
            isValidViewType        : function (a) {
                return Boolean(this.getViewSpec(a))
            },
            langDefaults           : null,
            loadingLevel           : 0,
            options                : null,
            overrides              : null,
            popLoading             : function () {
                --this.loadingLevel || this.trigger("loading", null, !1, this.view)
            },
            populateOptionsHash    : function () {
                var a,
                    b,
                    d,
                    e;
                a                 = ba(this.dynamicOverrides.lang, this.overrides.lang),
                b                 = Ab[a],
                b || (a = zb.defaults.lang, b = Ab[a] || {}),
                d                 = ba(this.dynamicOverrides.isRTL, this.overrides.isRTL, b.isRTL, zb.defaults.isRTL),
                e                 = d
                    ? zb.rtlDefaults
                    : {},
                this.dirDefaults  = e,
                this.langDefaults = b,
                this.options      = c([zb.defaults, e, b, this.overrides, this.dynamicOverrides]),
                Ra(this.options)
            },
            pushLoading            : function () {
                this.loadingLevel++ || this.trigger("loading", null, !0, this.view)
            },
            view                   : null,
            viewSpecCache          : null
        });
    zb.mixin(lb),
    zb.mixin({
        bindOption              : function (a, b) {
            this.bindOptions([a], b)
        },
        bindOptions             : function (a, b) {
            var c,
                d = {
                    func : b,
                    names: a
                };
            for (c = 0; c < a.length; c++) 
                this.registerOptionHandlerObj(a[c], d);
            this.triggerOptionHandlerObj(d)
        },
        optionHandlers          : null,
        registerOptionHandlerObj: function (a, b) {
            (this.optionHandlers[a] || (this.optionHandlers[a] = [])).push(b)
        },
        triggerOptionHandlerObj : function (a) {
            var b,
                c = a.names,
                d = [];
            for (b = 0; b < c.length; b++) 
                d.push(this.options[c[b]]);
            a
                .func
                .apply(this, d)
        },
        triggerOptionHandlers   : function (a) {
            var b,
                c = this.optionHandlers[a] || [];
            for (b = 0; b < c.length; b++) 
                this.triggerOptionHandlerObj(c[b])
        }
    }),
    zb.defaults        = {
        aspectRatio               : 1.35,
        buttonIcons               : {
            next    : "right-single-arrow",
            nextYear: "right-double-arrow",
            prev    : "left-single-arrow",
            prevYear: "left-double-arrow"
        },
        buttonText                : {
            day     : "day",
            month   : "month",
            next    : "next",
            nextYear: "next year",
            prev    : "prev",
            prevYear: "prev year",
            today   : "today",
            week    : "week",
            year    : "year"
        },
        dayPopoverFormat          : "LL",
        defaultAllDayEventDuration: {
            days: 1
        },
        defaultTimedEventDuration : "02:00:00",
        defaultView               : "month",
        dragOpacity               : .75,
        dragRevertDuration        : 500,
        dragScroll                : !0,
        dropAccept                : "*",
        endParam                  : "end",
        eventLimit                : !1,
        eventLimitClick           : "popover",
        eventLimitText            : "more",
        eventOrder                : "title",
        forceEventDuration        : !1,
        handleWindowResize        : !0,
        header                    : {
            center: "",
            left  : "title",
            right : "today prev,next"
        },
        isRTL                     : !1,
        lazyFetching              : !0,
        longPressDelay            : 1e3,
        monthYearFormat           : "MMMM YYYY",
        nextDayThreshold          : "09:00:00",
        scrollTime                : "06:00:00",
        startParam                : "start",
        theme                     : !1,
        themeButtonIcons          : {
            next    : "circle-triangle-e",
            nextYear: "seek-next",
            prev    : "circle-triangle-w",
            prevYear: "seek-prev"
        },
        timezone                  : !1,
        timezoneParam             : "timezone",
        titleRangeSeparator       : " – ",
        unselectAuto              : !0,
        weekends                  : !0,
        weekNumberCalculation     : "local",
        weekNumbers               : !1,
        weekNumberTitle           : "W",
        windowResizeDelay         : 200
    },
    zb.englishDefaults = {
        dayPopoverFormat: "dddd, MMMM D"
    },
    zb.rtlDefaults     = {
        buttonIcons     : {
            next    : "left-single-arrow",
            nextYear: "left-double-arrow",
            prev    : "right-single-arrow",
            prevYear: "right-double-arrow"
        },
        header          : {
            center: "",
            left  : "next,prev today",
            right : "title"
        },
        themeButtonIcons: {
            next    : "circle-triangle-w",
            nextYear: "seek-prev",
            prev    : "circle-triangle-e",
            prevYear: "seek-next"
        }
    };
    var Ab = Wa.langs = {};
    Wa.datepickerLang = function (b, c, d) {
        var e = Ab[b] || (Ab[b] = {});
        e.isRTL           = d.isRTL,
        e.weekNumberTitle = d.weekHeader,
        a.each(Bb, function (a, b) {
            e[a] = b(d)
        }),
        a.datepicker && (a.datepicker.regional[c] = a.datepicker.regional[b] = d, a.datepicker.regional.en = a.datepicker.regional[""], a.datepicker.setDefaults(d))
    },
    Wa.lang           = function (b, d) {
        var e,
            f;
        e = Ab[b] || (Ab[b] = {}),
        d && (e = Ab[b] = c([e, d])),
        f = Sa(b),
        a.each(Cb, function (a, b) {
            null == e[a] && (e[a] = b(f, e))
        }),
        zb.defaults.lang = b
    };
    var Bb = {
            buttonText     : function (a) {
                return {
                    next : da(a.nextText),
                    prev : da(a.prevText),
                    today: da(a.currentText)
                }
            },
            monthYearFormat: function (a) {
                return a.showMonthAfterYear
                    ? "YYYY[" + a.yearSuffix + "] MMMM"
                    : "MMMM YYYY[" + a.yearSuffix + "]"
            }
        },
        Cb = {
            dayOfMonthFormat    : function (a, b) {
                var c = a.longDateFormat("l");
                return c = c.replace(/^Y+[^\w\s]*|[^\w\s]*Y+$/g, ""),
                b.isRTL
                    ? c  += " ddd"
                    : c = "ddd " + c,
                c
            },
            extraSmallTimeFormat: function (a) {
                return a
                    .longDateFormat("LT")
                    .replace(":mm", "(:mm)")
                    .replace(/(\Wmm)$/, "($1)")
                    .replace(/\s*a$/i, "t")
            },
            hourFormat          : function (a) {
                return a
                    .longDateFormat("LT")
                    .replace(":mm", "")
                    .replace(/(\Wmm)$/, "")
                    .replace(/\s*a$/i, "a")
            },
            mediumTimeFormat    : function (a) {
                return a
                    .longDateFormat("LT")
                    .replace(/\s*a$/i, "a")
            },
            noMeridiemTimeFormat: function (a) {
                return a
                    .longDateFormat("LT")
                    .replace(/\s*a$/i, "")
            },
            smallTimeFormat     : function (a) {
                return a
                    .longDateFormat("LT")
                    .replace(":mm", "(:mm)")
                    .replace(/(\Wmm)$/, "($1)")
                    .replace(/\s*a$/i, "a")
            }
        },
        Db = {
            smallDayDateFormat: function (a) {
                return a.isRTL
                    ? "D dd"
                    : "dd D"
            },
            smallWeekFormat   : function (a) {
                return a.isRTL
                    ? "w[" + a.weekNumberTitle + "]"
                    : "[" + a.weekNumberTitle + "]w"
            },
            weekFormat        : function (a) {
                return a.isRTL
                    ? "w[ " + a.weekNumberTitle + "]"
                    : "[" + a.weekNumberTitle + " ]w"
            }
        };
    Wa.lang("en", zb.englishDefaults),
    Wa.sourceNormalizers = [],
    Wa.sourceFetchers    = [];
    var Eb = {
            cache   : !1,
            dataType: "json"
        },
        Fb = 1;
    zb.prototype.normalizeEvent = function (a) {},
    zb.prototype.getPeerEvents  = function (a, b) {
        var c,
            d,
            e = this.getEventCache(),
            f = [];
        for (c = 0; c < e.length; c++) 
            d = e[c],
            b && b._id === d._id || f.push(d);
        return f
    };
    var Gb = Wa.BasicView = xb.extend({
            computeRange         : function (a) {
                var b = xb
                    .prototype
                    .computeRange
                    .call(this, a);
                return /year|month/.test(b.intervalUnit) && (b.start.startOf("week"), b.start = this.skipHiddenDays(b.start), b.end.weekday() && (b.end.add(1, "week").startOf("week"), b.end = this.skipHiddenDays(b.end, -1, !0))),
                b
            },
            computeScrollerHeight: function (a) {
                return a - l(this.el, this.scroller.el)
            },
            dayGrid              : null,
            dayGridClass         : vb,
            dayNumbersVisible    : !1,
            getEventSegs         : function () {
                return this
                    .dayGrid
                    .getEventSegs()
            },
            getHitEl             : function (a) {
                return this
                    .dayGrid
                    .getHitEl(a)
            },
            getHitSpan           : function (a) {
                return this
                    .dayGrid
                    .getHitSpan(a)
            },
            hasRigidRows         : function () {
                var a = this.opt("eventLimit");
                return a && "number" != typeof a
            },
            headContainerEl      : null,
            headRowEl            : null,
            initialize           : function () {
                this.dayGrid  = this.instantiateDayGrid(),
                this.scroller = new yb({overflowX: "hidden", overflowY: "auto"})
            },
            instantiateDayGrid   : function () {
                var a = this
                    .dayGridClass
                    .extend(Hb);
                return new a(this)
            },
            prepareHits          : function () {
                this
                    .dayGrid
                    .prepareHits()
            },
            queryHit             : function (a, b) {
                return this
                    .dayGrid
                    .queryHit(a, b)
            },
            queryScroll          : function () {
                return this
                    .scroller
                    .getScrollTop()
            },
            releaseHits          : function () {
                this
                    .dayGrid
                    .releaseHits()
            },
            renderBusinessHours  : function () {
                this
                    .dayGrid
                    .renderBusinessHours()
            },
            renderDates          : function () {
                this.dayNumbersVisible      = this.dayGrid.rowCnt > 1,
                this.weekNumbersVisible     = this.opt("weekNumbers"),
                this.dayGrid.numbersVisible = this.dayNumbersVisible || this.weekNumbersVisible,
                this
                    .el
                    .addClass("fc-basic-view")
                    .html(this.renderSkeletonHtml()),
                this.renderHead(),
                this
                    .scroller
                    .render();
                var b = this
                        .scroller
                        .el
                        .addClass("fc-day-grid-container"),
                    c = a('<div class="fc-day-grid" />').appendTo(b);
                this
                    .el
                    .find(".fc-body > tr > td")
                    .append(b),
                this
                    .dayGrid
                    .setElement(c),
                this
                    .dayGrid
                    .renderDates(this.hasRigidRows())
            },
            renderDrag           : function (a, b) {
                return this
                    .dayGrid
                    .renderDrag(a, b)
            },
            renderEvents         : function (a) {
                this
                    .dayGrid
                    .renderEvents(a),
                this.updateHeight()
            },
            renderHead           : function () {
                this.headContainerEl = this
                    .el
                    .find(".fc-head-container")
                    .html(this.dayGrid.renderHeadHtml()),
                this.headRowEl       = this
                    .headContainerEl
                    .find(".fc-row")
            },
            renderSelection      : function (a) {
                this
                    .dayGrid
                    .renderSelection(a)
            },
            renderSkeletonHtml   : function () {
                return '<table><thead class="fc-head"><tr><td class="fc-head-container ' + this.widgetHeaderClass + '"></td></tr></thead><tbody class="fc-body"><tr><td class="' + this.widgetContentClass + '"></td></tr></tbody></table>'
            },
            scroller             : null,
            setGridHeight        : function (a, b) {
                b
                    ? j(this.dayGrid.rowEls)
                    : i(this.dayGrid.rowEls, a, !0)
            },
            setHeight            : function (a, b) {
                var c,
                    d,
                    g = this.opt("eventLimit");
                this
                    .scroller
                    .clear(),
                f(this.headRowEl),
                this
                    .dayGrid
                    .removeSegPopover(),
                g && "number" == typeof g && this
                    .dayGrid
                    .limitRows(g),
                c = this.computeScrollerHeight(a),
                this.setGridHeight(c, b),
                g && "number" != typeof g && this
                    .dayGrid
                    .limitRows(g),
                b || (this.scroller.setHeight(c), d = this.scroller.getScrollbarWidths(), (d.left || d.right) && (e(this.headRowEl, d), c = this.computeScrollerHeight(a), this.scroller.setHeight(c)), this.scroller.lockOverflow(d))
            },
            setRange             : function (a) {
                xb
                    .prototype
                    .setRange
                    .call(this, a),
                this.dayGrid.breakOnWeeks = /year|month|week/.test(this.intervalUnit),
                this
                    .dayGrid
                    .setRange(a)
            },
            setScroll            : function (a) {
                this
                    .scroller
                    .setScrollTop(a)
            },
            unrenderBusinessHours: function () {
                this
                    .dayGrid
                    .unrenderBusinessHours()
            },
            unrenderDates        : function () {
                this
                    .dayGrid
                    .unrenderDates(),
                this
                    .dayGrid
                    .removeElement(),
                this
                    .scroller
                    .destroy()
            },
            unrenderDrag         : function () {
                this
                    .dayGrid
                    .unrenderDrag()
            },
            unrenderEvents       : function () {
                this
                    .dayGrid
                    .unrenderEvents()
            },
            unrenderSelection    : function () {
                this
                    .dayGrid
                    .unrenderSelection()
            },
            updateWidth          : function () {
                this.weekNumbersVisible && (this.weekNumberWidth = k(this.el.find(".fc-week-number")))
            },
            weekNumberStyleAttr  : function () {
                return null !== this.weekNumberWidth
                    ? 'style="width:' + this.weekNumberWidth + 'px"'
                    : ""
            },
            weekNumbersVisible   : !1,
            weekNumberWidth      : null
        }),
        Hb = {
            renderBgIntroHtml    : function () {
                var a = this.view;
                return a.weekNumbersVisible
                    ? '<td class="fc-week-number ' + a.widgetContentClass + '" ' + a.weekNumberStyleAttr() + "></td>"
                    : ""
            },
            renderHeadIntroHtml  : function () {
                var a = this.view;
                return a.weekNumbersVisible
                    ? '<th class="fc-week-number ' + a.widgetHeaderClass + '" ' + a.weekNumberStyleAttr() + "><span>" + ca(a.opt("weekNumberTitle")) + "</span></th>"
                    : ""
            },
            renderIntroHtml      : function () {
                var a = this.view;
                return a.weekNumbersVisible
                    ? '<td class="fc-week-number" ' + a.weekNumberStyleAttr() + "></td>"
                    : ""
            },
            renderNumberIntroHtml: function (a) {
                var b = this.view;
                return b.weekNumbersVisible
                    ? '<td class="fc-week-number" ' + b.weekNumberStyleAttr() + "><span>" + this
                        .getCellDate(a, 0)
                        .format("w") + "</span></td>"
                    : ""
            }
        },
        Ib = Wa.MonthView = Gb.extend({
            computeRange : function (a) {
                var b,
                    c = Gb
                        .prototype
                        .computeRange
                        .call(this, a);
                return this.isFixedWeeks() && (b = Math.ceil(c.end.diff(c.start, "weeks", !0)), c.end.add(6 - b, "weeks")),
                c
            },
            isFixedWeeks : function () {
                var a = this.opt("weekMode");
                return a
                    ? "fixed" === a
                    : this.opt("fixedWeekCount")
            },
            setGridHeight: function (a, b) {
                b = b || "variable" === this.opt("weekMode"),
                b && (a *= this.rowCnt / 6),
                i(this.dayGrid.rowEls, a, !b)
            }
        });
    Xa.basic     = {
        "class": Gb
    },
    Xa.basicDay  = {
        duration: {
            days: 1
        },
        type    : "basic"
    },
    Xa.basicWeek = {
        duration: {
            weeks: 1
        },
        type    : "basic"
    },
    Xa.month     = {
        "class" : Ib,
        defaults: {
            fixedWeekCount: !0
        },
        duration: {
            months: 1
        }
    };
    var Jb = Wa.AgendaView = xb.extend({
            axisStyleAttr        : function () {
                return null !== this.axisWidth
                    ? 'style="width:' + this.axisWidth + 'px"'
                    : ""
            },
            axisWidth            : null,
            bottomRuleEl         : null,
            computeInitialScroll : function () {
                var a = b.duration(this.opt("scrollTime")),
                    c = this
                        .timeGrid
                        .computeTimeTop(a);
                return c = Math.ceil(c),
                c && c++,
                c
            },
            computeScrollerHeight: function (a) {
                return a - l(this.el, this.scroller.el)
            },
            dayGrid              : null,
            dayGridClass         : vb,
            getEventSegs         : function () {
                return this
                    .timeGrid
                    .getEventSegs()
                    .concat(this.dayGrid
                        ? this.dayGrid.getEventSegs()
                        : [])
            },
            getHitEl             : function (a) {
                return a
                    .component
                    .getHitEl(a)
            },
            getHitSpan           : function (a) {
                return a
                    .component
                    .getHitSpan(a)
            },
            getNowIndicatorUnit  : function () {
                return this
                    .timeGrid
                    .getNowIndicatorUnit()
            },
            headContainerEl      : null,
            initialize           : function () {
                this.timeGrid = this.instantiateTimeGrid(),
                this.opt("allDaySlot") && (this.dayGrid = this.instantiateDayGrid()),
                this.scroller = new yb({overflowX: "hidden", overflowY: "auto"})
            },
            instantiateDayGrid   : function () {
                var a = this
                    .dayGridClass
                    .extend(Lb);
                return new a(this)
            },
            instantiateTimeGrid  : function () {
                var a = this
                    .timeGridClass
                    .extend(Kb);
                return new a(this)
            },
            noScrollRowEls       : null,
            prepareHits          : function () {
                this
                    .timeGrid
                    .prepareHits(),
                this.dayGrid && this
                    .dayGrid
                    .prepareHits()
            },
            queryHit             : function (a, b) {
                var c = this
                    .timeGrid
                    .queryHit(a, b);
                return !c && this.dayGrid && (c = this.dayGrid.queryHit(a, b)),
                c
            },
            queryScroll          : function () {
                return this
                    .scroller
                    .getScrollTop()
            },
            releaseHits          : function () {
                this
                    .timeGrid
                    .releaseHits(),
                this.dayGrid && this
                    .dayGrid
                    .releaseHits()
            },
            renderBusinessHours  : function () {
                this
                    .timeGrid
                    .renderBusinessHours(),
                this.dayGrid && this
                    .dayGrid
                    .renderBusinessHours()
            },
            renderDates          : function () {
                this
                    .el
                    .addClass("fc-agenda-view")
                    .html(this.renderSkeletonHtml()),
                this.renderHead(),
                this
                    .scroller
                    .render();
                var b = this
                        .scroller
                        .el
                        .addClass("fc-time-grid-container"),
                    c = a('<div class="fc-time-grid" />').appendTo(b);
                this
                    .el
                    .find(".fc-body > tr > td")
                    .append(b),
                this
                    .timeGrid
                    .setElement(c),
                this
                    .timeGrid
                    .renderDates(),
                this.bottomRuleEl = a('<hr class="fc-divider ' + this.widgetHeaderClass + '"/>').appendTo(this.timeGrid.el),
                this.dayGrid && (this.dayGrid.setElement(this.el.find(".fc-day-grid")), this.dayGrid.renderDates(), this.dayGrid.bottomCoordPadding = this.dayGrid.el.next("hr").outerHeight()),
                this.noScrollRowEls = this
                    .el
                    .find(".fc-row:not(.fc-scroller *)")
            },
            renderDrag           : function (a, b) {
                return a
                    .start
                    .hasTime()
                    ? this
                        .timeGrid
                        .renderDrag(a, b)
                    : this.dayGrid
                        ? this
                            .dayGrid
                            .renderDrag(a, b)
                        : void 0
            },
            renderEvents         : function (a) {
                var b,
                    c,
                    d = [],
                    e = [],
                    f = [];
                for (c = 0; c < a.length; c++) 
                    a[c].allDay
                        ? d.push(a[c])
                        : e.push(a[c]);
                b = this
                    .timeGrid
                    .renderEvents(e),
                this.dayGrid && (f = this.dayGrid.renderEvents(d)),
                this.updateHeight()
            },
            renderHead           : function () {
                this.headContainerEl = this
                    .el
                    .find(".fc-head-container")
                    .html(this.timeGrid.renderHeadHtml())
            },
            renderNowIndicator   : function (a) {
                this
                    .timeGrid
                    .renderNowIndicator(a)
            },
            renderSelection      : function (a) {
                a
                    .start
                    .hasTime() || a
                    .end
                    .hasTime()
                    ? this
                        .timeGrid
                        .renderSelection(a)
                    : this.dayGrid && this
                        .dayGrid
                        .renderSelection(a)
            },
            renderSkeletonHtml   : function () {
                return '<table><thead class="fc-head"><tr><td class="fc-head-container ' + this.widgetHeaderClass + '"></td></tr></thead><tbody class="fc-body"><tr><td class="' + this.widgetContentClass + '">' + (this.dayGrid
                    ? '<div class="fc-day-grid"/><hr class="fc-divider ' + this.widgetHeaderClass + '"/>'
                    : "") + "</td></tr></tbody></table>"
            },
            scroller             : null,
            setHeight            : function (a, b) {
                var c,
                    d,
                    g;
                this
                    .bottomRuleEl
                    .hide(),
                this
                    .scroller
                    .clear(),
                f(this.noScrollRowEls),
                this.dayGrid && (this.dayGrid.removeSegPopover(), c = this.opt("eventLimit"), c && "number" != typeof c && (c = Mb), c && this.dayGrid.limitRows(c)),
                b || (d = this.computeScrollerHeight(a), this.scroller.setHeight(d), g = this.scroller.getScrollbarWidths(), (g.left || g.right) && (e(this.noScrollRowEls, g), d = this.computeScrollerHeight(a), this.scroller.setHeight(d)), this.scroller.lockOverflow(g), this.timeGrid.getTotalSlatHeight() < d && this.bottomRuleEl.show())
            },
            setRange             : function (a) {
                xb
                    .prototype
                    .setRange
                    .call(this, a),
                this
                    .timeGrid
                    .setRange(a),
                this.dayGrid && this
                    .dayGrid
                    .setRange(a)
            },
            setScroll            : function (a) {
                this
                    .scroller
                    .setScrollTop(a)
            },
            timeGrid             : null,
            timeGridClass        : wb,
            unrenderBusinessHours: function () {
                this
                    .timeGrid
                    .unrenderBusinessHours(),
                this.dayGrid && this
                    .dayGrid
                    .unrenderBusinessHours()
            },
            unrenderDates        : function () {
                this
                    .timeGrid
                    .unrenderDates(),
                this
                    .timeGrid
                    .removeElement(),
                this.dayGrid && (this.dayGrid.unrenderDates(), this.dayGrid.removeElement()),
                this
                    .scroller
                    .destroy()
            },
            unrenderDrag         : function () {
                this
                    .timeGrid
                    .unrenderDrag(),
                this.dayGrid && this
                    .dayGrid
                    .unrenderDrag()
            },
            unrenderEvents       : function () {
                this
                    .timeGrid
                    .unrenderEvents(),
                this.dayGrid && this
                    .dayGrid
                    .unrenderEvents()
            },
            unrenderNowIndicator : function () {
                this
                    .timeGrid
                    .unrenderNowIndicator()
            },
            unrenderSelection    : function () {
                this
                    .timeGrid
                    .unrenderSelection(),
                this.dayGrid && this
                    .dayGrid
                    .unrenderSelection()
            },
            updateSize           : function (a) {
                this
                    .timeGrid
                    .updateSize(a),
                xb
                    .prototype
                    .updateSize
                    .call(this, a)
            },
            updateWidth          : function () {
                this.axisWidth = k(this.el.find(".fc-axis"))
            }
        }),
        Kb = {
            renderBgIntroHtml  : function () {
                var a = this.view;
                return '<td class="fc-axis ' + a.widgetContentClass + '" ' + a.axisStyleAttr() + "></td>"
            },
            renderHeadIntroHtml: function () {
                var a,
                    b = this.view;
                return b.opt("weekNumbers")
                    ? (a = this.start.format(b.opt("smallWeekFormat")), '<th class="fc-axis fc-week-number ' + b.widgetHeaderClass + '" ' + b.axisStyleAttr() + "><span>" + ca(a) + "</span></th>")
                    : '<th class="fc-axis ' + b.widgetHeaderClass + '" ' + b.axisStyleAttr() + "></th>"
            },
            renderIntroHtml    : function () {
                var a = this.view;
                return '<td class="fc-axis" ' + a.axisStyleAttr() + "></td>"
            }
        },
        Lb = {
            renderBgIntroHtml: function () {
                var a = this.view;
                return '<td class="fc-axis ' + a.widgetContentClass + '" ' + a.axisStyleAttr() + "><span>" + (a.opt("allDayHtml") || ca(a.opt("allDayText"))) + "</span></td>"
            },
            renderIntroHtml  : function () {
                var a = this.view;
                return '<td class="fc-axis" ' + a.axisStyleAttr() + "></td>"
            }
        },
        Mb = 5,
        Nb = [
            {
                hours: 1
            }, {
                minutes: 30
            }, {
                minutes: 15
            }, {
                seconds: 30
            }, {
                seconds: 15
            }
        ];
    return Xa.agenda = {
        "class" : Jb,
        defaults: {
            allDaySlot      : !0,
            allDayText      : "all-day",
            maxTime         : "24:00:00",
            minTime         : "00:00:00",
            slotDuration    : "00:30:00",
            slotEventOverlap: !0
        }
    },
    Xa.agendaDay     = {
        duration: {
            days: 1
        },
        type    : "agenda"
    },
    Xa.agendaWeek    = {
        duration: {
            weeks: 1
        },
        type    : "agenda"
    },
    Wa
});