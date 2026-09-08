package com.chessmaster.play

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.model.Square
import com.chessmaster.play.viewmodel.LearnViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ViewModelScratchTest {
    
    @Test
    fun testPromotionViewModel() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = LearnViewModel(app)
        
        viewModel.loadLesson("promotion")
        // skip explanation phases
        while (viewModel.lessonPhase.value.name == "EXPLANATION") {
            viewModel.nextStep()
        }
        
        // We are in practice phase
        println("Target moves: ${viewModel.javaClass.getDeclaredField("_practiceTargetMoves").apply{isAccessible=true}.get(viewModel)}")
        
        // Select e7
        val e7 = Square(4, 6)
        viewModel.onSquareClicked(e7)
        println("Selected square: ${viewModel.selectedSquare.value}")
        println("Legal moves from e7:")
        viewModel.legalMoves.value.forEach { println(" - ${it.from}->${it.to} prom=${it.promotionTo}") }
        
        // Click e8
        val e8 = Square(4, 7)
        viewModel.onSquareClicked(e8)
        
        println("Practice Error: ${viewModel.practiceMoveError.value}")
        println("Board piece at e8: ${viewModel.boardState.value.getPiece(e8)}")
    }
}
