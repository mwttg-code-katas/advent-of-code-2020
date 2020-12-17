package de.advent.of.code.day17

import scala.annotation.tailrec

final case class State(activeCubes: Set[Position])

object CycleSimulator {

  def execute(input: Vector[String],
              maxIterations: Int): State = {
    @tailrec
    def loop(iteration: Int,
             state    : State): State = {
      if (iteration == maxIterations) {
        state
      } else {
        val nextState = nextCycle(state)

        loop(iteration + 1, nextState)
      }
    }

    val activeCubes = Position.initActiveCubes(input)
    loop(0, State(activeCubes))
  }

  private def nextCycle(currentState: State): State = {
    val currentActiveCubes    = currentState.activeCubes
    val currentEmptyPositions = createEmptyPositions(currentActiveCubes)
    val remainingCubes        = remainingActiveCubes(currentActiveCubes)
    val newCreatedCubes       = createCubes(currentEmptyPositions, currentActiveCubes)
    val nextStepCubes         = remainingCubes.union(newCreatedCubes)

    State(nextStepCubes)
  }

  private def createEmptyPositions(activeCubes : Set[Position]) = {
    activeCubes.flatMap(position => Position.getInactiveNeighbourhoodOf(position, activeCubes))
  }

  private def createCubes(emptyPositions    : Set[Position],
                          activeCubes       : Set[Position]) = {
    emptyPositions.flatMap(position => isNewCube(position, activeCubes))
  }

  private def isNewCube(position: Position,
                        activeCubes: Set[Position]) = {
    val neighbours = Position.getActiveNeighbourhoodOf(position, activeCubes)
    if (neighbours.size == 3) {
      Some(position)
    } else {
      None
    }
  }

  private def remainingActiveCubes(activeCubes: Set[Position]) = {
    activeCubes.flatMap(position => isRemainingCube(position, activeCubes))
  }

  private def isRemainingCube(position: Position,
                              activeCubes                    : Set[Position]) = {
    val neighbours = Position.getActiveNeighbourhoodOf(position, activeCubes)
    if (neighbours.size == 2 || neighbours.size == 3) {
      Some(position)
    } else {
      None
    }
  }
}
