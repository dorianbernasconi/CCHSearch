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

     return fetch('http://localhost:4567/query/elastic/string?' + params, {
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
          return res as Array<Element>
      })
      .then( res => {
          const jsonArray :any  = res;
          return jsonArray["documents"] 
      })
}

  
