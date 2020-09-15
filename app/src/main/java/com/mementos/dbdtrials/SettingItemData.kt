package com.mementos.dbdtrials

// Класс для создания итемов в списке настроек. Дополнительно используется для заголовков,
// поэтому содержит в себе is_header
data class SettingItemData(
    val code: String,
    val type_perk: String,
    val type_data: String,
    val is_active: Int = 0,
    val is_header: Boolean = false
)