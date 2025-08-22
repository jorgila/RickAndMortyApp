package com.jorgila.rickandmortyapp

expect fun getCurrentTarget() : Target

enum class Target {
    iOS, Android, Desktop
}

fun isDesktop() = getCurrentTarget() == Target.Desktop
fun isAndroid() = getCurrentTarget() == Target.Android
fun isIOS() = getCurrentTarget() == Target.iOS
fun isMobile() = getCurrentTarget() == Target.Android || getCurrentTarget() == Target.iOS