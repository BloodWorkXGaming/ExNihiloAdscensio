package exnihilocreatio.recipes.defaults

import exnihilocreatio.texturing.Color
import exnihilocreatio.util.ItemInfo
import exnihilocreatio.util.OreDictUtil

enum class EnumModdedMetals(val color: Color) {
    ALUMINUM(Color("BFBFBF")),
    ARDITE(Color("FF751A")),
    COBALT(Color("3333FF")),
    COPPER(Color("FF9933")),
    LEAD(Color("330066")),
    NICKEL(Color("FFFFCC")),
    SILVER(Color("F2F2F2")),
    TIN(Color("E6FFF2")),
    TUNGSTEN(Color("1C1C1C")),
    URANIUM(Color("4E5B43")),
    ZINC(Color("A59C74")),
    TITANIUM(Color("BD87CA")),
    THORIUM(Color("333333")),
    BORON(Color("939393")),
    LITHIUM(Color("EDEDED")),
    BERYLLIUM(Color("E5ECDD")),
    ZIRCONIUM(Color("E6E8C7")),
    MAGNESIUM(Color("F8DEF8"));


    fun getRegistryName(): String {
        return name.toLowerCase()
    }

    private fun getConcatableName(): String {
        return getRegistryName().capitalize()
    }

    fun getOreName(): String {
        return "ore" + getConcatableName()
    }

    private fun getIngotName(): String {
        return "ingot" + getConcatableName()
    }

    private fun getDustName(): String {
        return "dust" + getConcatableName()
    }

    fun getIngot(): ItemInfo? {
        return OreDictUtil.getOreDictEntry(getIngotName())
    }

    fun getDust(): ItemInfo? {
        return OreDictUtil.getOreDictEntry(getDustName())
    }


}