class Tile{
    constructor(position, value){
        this.x = position.x;
        this.y = position.y;
        this.value = value;
    }
    toString(){
        console.log('x=' + this.x + ' y=' + this.y + ' value=' + this.value);
    }
}

export {
    Tile
}