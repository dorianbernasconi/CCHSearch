
export function useless(){
    console.log("f")
}


export type SolrElement = {
    emplacement: String,
    thumbnail:String,
    modtime : String,
    _root_ : String,
    link : Array<String>,
    affaire: String,
    ftype : String,
    filename: String,
    content_type:String,
    _version_:Number,
    racifile: String,
    id: String,
    kh: String,
}

export type CardElement = {
    score:number,
    filepath: "",
    id: string
    filename: string
    src: string
    pageNumber: number
    lastModificationDate:number,
    affaire:string[],
    echelle: string
    price:number,
    wordList: string[]
    modtime: String,
    kh: Boolean
    jpgname: string
}

/*
// Document from Solr
export interface SolrElement = {
    emplacement: string
    thumbnail:string
    modtime : string
    _root_ : string
    link : string
    affaire: string
    ftype : string
    filename: string
    content_type:string
    _version_:string
    racifile: string
    id: string
    kh: string
}

// Document from Elastic
export interface DetailElement = {
    score:number,
    id: string
    filename: string
    filePath: string
    pageNumber: number
    lastModificationDate:number,
    affairename:string,
    echelle: number
    price:number,
    affaire: string
    wordList: string[]
    modtime: String,
    kh: Boolean
    jpgname: string
}

export interface DocumentElement = SolrElement | DetailElement | Page;



export interface Page {

    fileName: string
    filePath: string
    pageNumber: number
    jpgName: string
    lastModificationDate: number
    echelle: number
    wordList: string[]
}
*/