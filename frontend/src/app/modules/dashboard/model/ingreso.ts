export class Ingreso {
    fecha: string;
    categoria: string;
    descripcion:string;
    importe:number;

    constructor(fecha: string, categoria:string, descripcion:string, importe:number) {
        this.fecha = fecha;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.importe = importe;
    }
}