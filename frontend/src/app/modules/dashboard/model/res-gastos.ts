export class ResGastos {
    id:number;
    date: string;
    categoryName: string;
    description:string;
    amount:number;

    constructor(fecha: string, category:string, description:string, amount:number, id:number) {
        this.date = fecha;
        this.categoryName = category;
        this.description = description;
        this.amount = amount;
        this.id = id;
    }
}