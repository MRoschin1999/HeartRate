class piece_drop {
    var x: Int = 0;
    var y: Int = 0;
    var mark: Int = 1;
    var win: Boolean = false;

    constructor(x: Int, y: Int, mark: Int, win: Boolean) {
        this.x = x;
        this.y = y;
        this.mark = mark;
        this.win = win;
    }

    fun get_x(): Int {
        return this.x;
    }

    fun get_y(): Int {
        return this.y;
    }

    fun get_mark(): Int {
        return this.mark;
    }
}