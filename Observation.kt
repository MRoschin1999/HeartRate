class Observation {
    var mark: Int = 1;
    var board: Array<Array<Int>> = Array(6, { Array(7, {0}) });

    constructor(n: Int, m: Int) {
        this.board = Array(n, { Array(m, {0}) });
        this.mark = 1;
    }

    fun next_move(config: Config, col: Int): piece_drop{
        var row = 0;
        var temp = this.board.copyOf();
        for (i in config.get_n() downTo 0 step 1){
            if (temp[i][col] == 0){
                row = i;
                break
            }
        }
        temp[row][col] = this.mark;
        var pieceDrop: piece_drop = piece_drop(row, col, this.mark, this.check_win());
        this.mark = this.mark % 2 + 1
        return  pieceDrop
    }

    fun check_win(config: Config):Boolean{
//        Horizontal
        for (row in config.get_n() downTo 0 step 1){
            for (col in 0..config.get_m()-config.get_row_to_win()-1){

            }
        }
    }
}