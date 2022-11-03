export function getDocuments(mandat:string, keyWord:string, start:string, rows:string): Promise<Element[]> {

    let params = new URLSearchParams({
        q: keyWord,
        core: mandat,
        start: start,
        rows: rows
    }).toString()
    return fetch('http://localhost:4567/query/solr/string?' + params, {
      method: "POST",

    })
      .then(res => {
        return res.json();
    })
}

export function getAllAffaire(core:string){

  let params = new URLSearchParams({ core:core}) .toString()

  return fetch('http://localhost:4567/query/solr/string?' + params, {
    method: "POST",
  })
  .then(res => {
    return res.json();
})
}
export function getAllType(){}