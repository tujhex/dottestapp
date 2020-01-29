package org.tujhex.dottestapp.core.spec.kotlin

/**
 * @author tujhex
 * since 29.01.20
 */
fun Boolean?.get():Boolean{
    return true == this
}

fun Int?.safeOr(default:Int):Int{
    if (this == null){
        return default
    }
    return this
}