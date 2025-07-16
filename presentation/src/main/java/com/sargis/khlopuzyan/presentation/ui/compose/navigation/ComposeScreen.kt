package com.sargis.khlopuzyan.presentation.ui.compose.navigation

sealed class ComposeScreen(val route: String) {
    object JetpackComposeMainScreen : ComposeScreen("jetpack_compose_main_screen")
    object ComposeStateTestScreen : ComposeScreen("compose_state_test_screen")
    object ColorBoxScreen : ComposeScreen("color_box_screen")
    object StylingTextScreen : ComposeScreen("styling_text_screen")
    object TextfieldsButtonsSnackbarsScreen : ComposeScreen("textfields_buttons_snackbars_screen")
    object EffectHandlersScreen : ComposeScreen("effect_handlers_screen")
    object KotlinFlowsScreen : ComposeScreen("kotlin_flows_screen")
    object KotlinHotFlowsScreen : ComposeScreen("kotlin_hot_flows_screen")
    object KotlinHotFlowsVsColdFlowsScreen : ComposeScreen("kotlin_hot_flows_vs_cold_flows_screen")
    object CoroutineCancellationAndExceptionHandlingScreen : ComposeScreen("coroutine_cancellation_and_exception_handling_screen")
    object PerformanceOptimizationWithStableAndImmutable : ComposeScreen("performance_optimization_with_stable_and_immutable")
}