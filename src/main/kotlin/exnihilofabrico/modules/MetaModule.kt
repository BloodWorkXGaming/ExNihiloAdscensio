package exnihilofabrico.modules

import exnihilofabrico.api.registry.*

object MetaModule: ICompatModule {
    var modules: List<ICompatModule> = listOf(ExNihilo)

    override fun registerBarrelAlchemy(registry: IBarrelAlchemyRegistry) = modules.forEach { it.registerBarrelAlchemy(registry) }
    override fun registerBarrelMilking(registry: IBarrelMilkingRegistry) = modules.forEach { it.registerBarrelMilking(registry) }
    override fun registerCrucibleHeat(registry: ICrucibleHeatRegistry) = modules.forEach { it.registerCrucibleHeat(registry) }
    override fun registerCrucibleStone(registry: ICrucibleRegistry) = modules.forEach { it.registerCrucibleStone(registry) }
    override fun registerCrucibleWood(registry: ICrucibleRegistry) = modules.forEach { it.registerCrucibleWood(registry) }
    override fun registerSieve(registry: ISieveRegistry) = modules.forEach { it.registerSieve(registry) }
    override fun registerCrook(registry: IToolRegistry) = modules.forEach { it.registerCrook(registry) }
    override fun registerHammer(registry: IToolRegistry) = modules.forEach { it.registerHammer(registry) }
    override fun registerWitchWaterEntity(registry: IWitchWaterEntityRegistry) = modules.forEach { it.registerWitchWaterEntity(registry) }
    override fun registerWitchWaterFluid(registry: IWitchWaterFluidRegistry) = modules.forEach { it.registerWitchWaterFluid(registry) }
}