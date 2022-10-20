
export type DocumentElement = {
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


export type DetailElement = {
    id: string
    fileName: string
    filePath: string
    affaire: string
    modtime: String,
    sambaLink: String
    knowHow: Boolean
    jpgName: string
}

export type Element = DocumentElement | DetailElement;



export interface Page {

    fileName: string
    filePath: string
    pageNumber: number
    jpgName: string
    lastModificationDate: number
    echelle: number
    wordList: string[]
}


export function getDocument(keyWord: string, core:string,start:string,rows:string): Promise<Element[]> {

    let params = new URLSearchParams({
        q: keyWord,
        core: core,
        start: start,
        rows: rows
    }).toString()
    
    return fetch('http://localhost:4567/query/solr/string?' + params, {
      method: "POST",
  
    })
      .then(res => res.json())
      .then(res => {
        console.log(res)
        return res as Array<Element>
    })
    .then( res => {
        const jsonArray :any  = res;
        return jsonArray["documents"] 
    })
}

export function getDetail(keyWord: string){

}

  
