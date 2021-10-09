package com.example.healthalarm.dataSets

import java.util.*

object NotificationDataSet {
    var NotificationData = arrayOf(
        "Take a break, myfriend",
        "Take care of your health",
        "Don't forget to drink water",
        "Sit straight, don't slouch or slump, look straight ahead",
        "Give your eyes a chance to reset their focus.",
        "Remember to check your posture",
        "sit up straight, be healthy.",
        "How's your posture, friend",
        "Check your posture, buddy",
        "Check yourself, mate"
    )
    var ArabicNotificationData = arrayOf(
        "خذ استراحة يا صديقي",
        "اعتني بصحتك",
        "لا تنسى شرب الماء",
        "اجلس مستقيماً ، انظر للأمام بشكل مستقيم",
        "امنح عينيك فرصة لإعادة ضبط تركيزهما.",
        " تذكر أن تتحقق من وضعيتك ",
        "اجلس مستقيما ، كن بصحة جيدة.",
        "امنح عينيك فرصة لإعادة ضبط تركيزهما.",
        "امنح عينيك فرصة لإعادة ضبط تركيزهما."
    )

    @JvmStatic
    fun getrandomAvice(): String {
        val randomNum = Random().nextInt(NotificationData.size)
        return NotificationData[randomNum]
    }
}