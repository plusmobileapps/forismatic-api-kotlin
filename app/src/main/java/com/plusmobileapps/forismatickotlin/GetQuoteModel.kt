package com.plusmobileapps.forismatickotlin

import kotlinx.serialization.Serializable

//{
//    "quoteText": "Difficulties are meant to rouse, not discourage. The human spirit is to grow strong by conflict. ",
//    "quoteAuthor": "William Channing",
//    "senderName": "",
//    "senderLink": "",
//    "quoteLink": "http://forismatic.com/en/3e481c1017/"
//}
@Serializable
data class GetQuoteModel(
    val quoteText: String,
    val quoteAuthor: String,
    val senderName: String,
    val senderLink: String,
    val quoteLink: String
)