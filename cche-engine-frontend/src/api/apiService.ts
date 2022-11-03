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
        return res
    })
    .then( res => {

        return res as any
    })
}

export function getElasticDetail(keyWord: string, index:string,min:string,max:string,field:string){

    let params = new URLSearchParams({
        q: keyWord,
        index: index,
        min: min,
        max: max,
        field: field
    }).toString()

     return fetch('http://localhost:4567/query/elastic/request/string?' + params, {
        method: "POST",
    
      })
        .then(res => res.json())
        .then(res => {
          console.log(res)
          return res 
      })
      .then( res => {
          const jsonArray :any  = res;
          return jsonArray["documents"] as Array<CardElement>
      })


}

export function getElasticPlan(keyWord: string, index:string,min:string,max:string, field:string){

    let params = new URLSearchParams({
        q: keyWord,
        index: index,
        min: min,
        max: max,
        field: field

    }).toString()

    return fetch('http://localhost:4567/query/elastic/request/string?' + params, {
        method: "POST",
    
      })
        .then(res => res.json())
        .then(res => {
          return res as Array<Element>
      })
      .then( res => {
          const jsonArray :any  = res;
          return jsonArray["documents"]
      })
}


export function getSimilarity(filepath: string,){

  let params = new URLSearchParams({
      q: filepath
  }).toString()

  return fetch('http://localhost:4567/query/elastic/request/string?' + params, {
      method: "POST",
  
    })
      .then(res => res.json())
      .then(res => {
        return res as Array<Element>
    })
    .then( res => {
        const jsonArray :any  = res;
        return jsonArray["documents"]
    })
}
  
