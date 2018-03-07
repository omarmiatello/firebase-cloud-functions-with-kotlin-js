import kotlin.js.Date

// JS generic utils

fun also(obj: dynamic, onReady: (dynamic) -> dynamic) = obj.unsafeCast<Any>().apply { onReady(asDynamic()) }.asDynamic()

fun getWeekNumber(date: Date) = eval("""
var d = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
var dayNum = d.getUTCDay() || 7;
d.setUTCDate(d.getUTCDate() + 4 - dayNum);
var yearStart = new Date(Date.UTC(d.getUTCFullYear(),0,1));
return Math.ceil((((d - yearStart) / 86400000) + 1)/7);""").unsafeCast<Int>()

fun getDateOfWeek(week: Int, year: Int) = js("""
var simple = new Date(year, 0, 1 + (week - 1) * 7);
var dow = simple.getDay();
var iso = simple;
if (dow <= 4) iso.setDate(simple.getDate() - simple.getDay() + 1);
else iso.setDate(simple.getDate() + 8 - simple.getDay());
return iso;""").unsafeCast<Date>()

// Json

fun toJson(obj: dynamic) = JSON.stringify(obj) { key, value -> value ?: undefined }


// Arrays / Map

fun jsMap(init: (dynamic) -> Unit) = Any().apply { init(asDynamic()) }.asDynamic()

fun keys(obj: dynamic) = js("Object").keys(obj).unsafeCast<Array<String>>()
