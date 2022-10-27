import type { CardElement } from "./type"


export function getSolrDocument(keyWord: string, core:string,start:string,rows:string): Promise<Element[]> {

    let params = new URLSearchParams({
        q: keyWord,
        core: core,
        start: start,
        rows: rows
    }).toString()
    
    console.log("OH")
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

export function getElastic(keyWord: string, index:string,min:string,max:string){

    let params = new URLSearchParams({
        q: keyWord,
        index: index,
        size : "50",
        min: min,
        max: max,
        all: "true"
    }).toString()


    let cards : Array<CardElement> = [{
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filenameB",
        affaire: "affaireB",
        echelle: 1,
        id: "idB",
        pageNumber: 1,
        price: 1,
        wordList: ["wordListB"],
        modtime: "modtimeB",
        kh: true,
        jpgname: "jpgnameB",
        lastModificationDate: 1,
        score: 1

    },];

     return fetch('http://localhost:4567/query/elastic/string?' + params, {
        method: "POST",
    
      })
        .then(res => res.json())
        .then(res => {
          console.log(res)
          return res as Array<CardElement>
      })

      /*
      .then( res => {
          const jsonArray :any  = res;
          return jsonArray["documents"] 
      })*/


}

export function getElasticPlan(keyWord: string, index:string,min:string,max:string){

    
    let params = new URLSearchParams({
        q: keyWord,
        index: index,
        min: min,
        rows: max
    }).toString()

    return fetch('http://localhost:4567/query/elastic/plan/string?' + params, {
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

  
