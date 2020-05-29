class Observation {
    var mark: Int = 1;
    var board: Array<Array<Int>> = Array(6, { Array(7, {0}) });

    constructor(config: Config) {
        this.board = Array(config.get_n(), { Array(config.get_m(), {0}) });
        this.mark = 1;
    }

    fun nextMove(config: Config, col: Int): piece_drop{
        var row = 0;
        var temp = this.board.copyOf();
        for (i in config.get_n() downTo 0 step 1){
            if (temp[i][col] == 0){
                row = i;
                break
            }
        }
        temp[row][col] = this.mark;
        var pieceDrop = piece_drop(row, col, this.mark, this.checkWin(config));
        this.mark = this.mark % 2 + 1
        return  pieceDrop
    }

    private fun checkWin(config: Config):Boolean{
        var res = true
//        Horizontal
        for (row in 0..config.get_n()) {
            for (col in 0 until config.get_m() - config.get_row_to_win()) {
                for (i in 0..config.get_row_to_win()) {
                    res = res and (this.board[row][col + i] == this.mark)
                }
                if (res) return res
            }
        }  

//        Vertical
        for (col in 0..config.get_m()) {
            for (row in 0 until config.get_n() - config.get_row_to_win()) {
                for (i in 0..config.get_row_to_win()) {
                    res = res and (this.board[row + i][col] == this.mark)
                }
                if (res) return res
            }
        }
//        Diagonal positive
        for (row in 0..config.get_n() - config.get_row_to_win()) {
            for (col in 0..config.get_m()-config.get_row_to_win()) {
                for (i in 0..config.get_row_to_win()) {
                    res = res and (this.board[row + i][col + i] == this.mark)
                }
                if (res) return res
            }
        }
        
//        Diagonal negative
        for (row in config.get_n() - config.get_row_to_win() downTo 0 step 1) {
            for (col in 0..config.get_m()-config.get_row_to_win()) {
                for (i in 0..config.get_row_to_win()) {
                    res = res and (this.board[row - i][col + i] == this.mark)
                }
                if (res) return res
            }
        }
        return false
    }
}
