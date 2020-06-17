class Agent {
    var A = 10000000
    var B = 1000
    var C = 0
    var D = -1000
    var E = -100000

    var config: Config

    constructor(config: Config) {
        this.config = config
    }
    
    fun calculate_the_move(level: Int, observation: Observation): Int {
        var obs: Observation
        var piece: piece_drop
        when (level) {
            1 -> {
                var i: Int
                for (i in 0..observation.config.get_m()) {
                    obs = observation.copyOf()
                    piece = obs.nextMove(config, i)
                    if (piece.win) return i
                }
                for (i in 0..observation.config.get_m()) {
                    obs = observation.copyOf()
                    obs.mark = obs.mark % 2 + 1
                    piece = obs.nextMove(config, i)
                    if (piece.win) return i
                }
                return (0..config.get_m()).random()
            }
            2 -> {
                var i: Int
                for (i in 0..observation.config.get_m()) {
                    obs = observation.copyOf()
                    obs.mark = obs.mark % 2 + 1
                    piece = obs.nextMove(config, i)
                    if (piece.win) return i
                }
                var weights: Array<Int> = Array(7, {0})
                for (i in 0..observation.config.get_m()) {
                    obs = observation.copyOf()
                    piece = obs.nextMove(config, i)
                    weights[i] = value_the_situation(obs)
                }
                val maxIdx = weights.indices.maxBy { weights[it] } ?: -1
                return maxIdx
            }
        }
        return 0
    }

    fun value_the_situation(observation: Observation):Int{
        return  A * count_the_ns(observation, 4, 2) +
                B * count_the_ns(observation, 3, 2) +
                C * count_the_ns(observation, 2, 2) +
                D * count_the_ns(observation, 2, 1) +
                E * count_the_ns(observation, 3, 1)
    }

    fun count_the_ns(observation: Observation, n: Int, mark:Int): Int {
        var res: Boolean
        var counter = 0
        var temp = observation.board.copyOf()
        for (row in 0 until config.get_n()) {
            for (col in 0 .. (config.get_m() - n)) {
                res = true
                for (i in 0 until n) {
                    res = res and (observation.board[row][col + i] == mark)
                }
                if (res) counter++
            }
        }
//        Vertical
        for (col in 0 until config.get_m()) {
            for (row in 0 .. (config.get_n() - n)) {
                res = true
                for (i in 0 until n) {
                    res = res and (observation.board[row + i][col] == mark)
                }
                if (res) counter++
            }
        }
//        Diagonal positive
        for (row in 0 .. (config.get_n() - n)) {
            for (col in 0 .. config.get_m() - n) {
                res = true
                for (i in 0 until n) {
                    res = res and (observation.board[row + i][col + i] == mark)
                }
                if (res) counter++
            }
        }
//        Diagonal negative
        for (row in config.get_n() - 1 downTo n - 1 step 1) {
            for (col in (0 .. config.get_m() - n)) {
                res = true
                for (i in 0 until n) {
                    res = res and (observation.board[row - i][col + i] == mark)
                }
                if (res) counter++
            }
        }
        return counter
    }
}
