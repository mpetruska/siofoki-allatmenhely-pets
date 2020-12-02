const { useEffect, useState } = require("react")
const axios                   = require('axios')

function SearchHeader() {
  return (
    <header className="header">
      <div className="row headerRow">
        <div className="col-3 align-self-center logo">
          <img src="resources/img/menulogo_feher.png" alt="Siófoki Állatmenhely logoja" className="logo" />
        </div>
        <div className="col-6 align-self-center pageTitle">
          <span className="pageTitle">Kutyakeresés</span>
        </div>
        <div className="col-3 pageLanguage align-self-center">
          <a className="language-link-item" href="index.php?lang=hu" style={{"color": 'white'}} data-darkreader-inline-color="">HU</a> | 
          <a className="language-link-item" href="index.php?lang=de">DE</a>
        </div>
      </div>
    </header>
  )
}

function SearchParameters() {
  return (
    <div>
      <div className="row searchSection justify-content-center" name="_searchWindow">
        <div className="col-lg-2 col-md-2 col-sm-4 col-4 searchColumn">
          <div className="searchLabel">
            Ivar:
          </div>
          <div className="searchItem">
            <input id="Kan" type="checkbox" className="common_selector _sex" value="2" />
            <label for="Kan">Kan</label>
            <input id="Szuka" type="checkbox" className="common_selector _sex" value="3" />
            <label for="Szuka">Szuka</label>
          </div>
        </div>
        <div className="col-lg-2 col-md-2 col-sm-4 col-4 searchColumn">
          <div className="searchLabel">
            Kor:
          </div>
          <div className="searchItem">
	          <input id="Kölyök" type="checkbox" className="common_selector _age" value="2" />
            <label for="Kölyök">Kölyök</label>
            <input id="Felnőtt" type="checkbox" className="common_selector _age" value="3" />
            <label for="Felnőtt">Felnőtt</label>
            <input id="Idős" type="checkbox" className="common_selector _age" value="4" />
            <label for="Idős">Idős</label>
          </div>
        </div>
        <div className="col-lg-2 col-md-2 col-sm-4 col-4 searchColumn">
          <div className="searchLabel">
            Méret:
          </div>
          <div className="searchItem">
            <input id="Kicsi" type="checkbox" className="common_selector _size" value="2" />
            <label for="Kicsi">Kicsi</label>
            <input id="Közepes" type="checkbox" className="common_selector _size" value="3" />
            <label for="Közepes">Közepes</label>
            <input id="Nagy" type="checkbox" className="common_selector _size" value="4" />
            <label for="Nagy">Nagy</label>
          </div>
        </div>
        <div className="col-lg-2 col-md-2 col-sm-4 col-4 searchColumn">
          <div className="searchLabel">
            Szőrzet:
          </div>
          <div className="searchItem">
            <input id="Sima" type="checkbox" className="common_selector _haired" value="2" />
            <label for="Sima">Sima</label>
            <input id="Szálkás" type="checkbox" className="common_selector _haired" value="3" />
            <label for="Szálkás">Szálkás</label>
            <input id="Hosszú" type="checkbox" className="common_selector _haired" value="4" />
            <label for="Hosszú">Hosszú</label>
          </div>
        </div>
        <div className="col-lg-4 col-md-4 col-sm-8 col-8 searchColumn">
          <div className="searchLabel">
            Státusz:
          </div>
          <div className="searchItem">
            <input id="Örökbe fogadható" type="checkbox" className="common_selector _status" value="2" />
            <label for="Örökbe fogadható">Örökbe fogadható</label>
            <input id="Gazdajelöltje van" type="checkbox" className="common_selector _status" value="3" />
            <label for="Gazdajelöltje van">Gazdajelöltje van</label>
            <input id="Eredeti gazdáját keresi" type="checkbox" className="common_selector _status" value="4" />
            <label for="Eredeti gazdáját keresi">Eredeti gazdáját keresi</label>
          </div>
        </div>
      </div>
      <div className="row nameSearch">
        <div className="col textInput">
          <div className="searchText">
            Név:
            <input class="common_input_name" type="text" id="_name" name="_name" />
          </div>
        </div>
      </div>
      <a className="kedvencekBtn" href="./templates/favouriteDogs.php" target="_self">
        <p class="kedvenceimParagraph">
          <i className="fa fa-star"></i><br />
          Kedvenceim
        </p>
      </a>
    </div>
  )
}

function DogModal(props) {
  return (
    <div className="modal fade align-self-center" id="dogProfileModal1" tabindex="-1" role="dialog" aria-labelledby="dogProfileModalTitle" aria-hidden="true" style={{"padding": "0 !important"}}>
      <div className="modal-dialog modal-dialog-centered modal-xl" role="document">
        <div className="modal-content" id="modal_opened">
          <div className="modal-header">
            <p className="modal-title self-align-center h3" id="dogProfileModalTitle">Lucas adatlapja</p>
            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div className="modal-body">
            <div className="container-fluid pictureGallery">
              <div className="row">
                <div className="col-sm-12 col-md-4">
                  <div className="modalImg" style={{"background-image": "url('./resources/img/dogPics/1/lucas1.jpg')"}}></div>
                </div>
                <div className="col-sm-12 col-md-4">
                  <div className="modalImg" style={{"background-image": "url('./resources/img/dogPics/1/lucas2.jpg')"}}></div>
                </div>
                <div className="col-sm-12 col-md-4">
                  <div className="modalImg" style={{"background-image": "url('./resources/img/dogPics/1/2306_cut.jpg')"}}></div>
                </div>
              </div>
            </div>
            <div className="container">
              <div className="row youtubeContainer">
                <button className="col-lg-6 col-10 justify-content-center align-self-center youtube gallery" onclick="window.open('agsu')" type="button">
                  <i className="fas fa-camera"></i>Nézd meg a Galériámat
                </button>
              </div>
            </div>
            <div className="row">
              <div className="nextDogButton buttonNext" data-target="#dogProfileModal" data-index="1" data-toggle="tooltip" data-placement="top" title="Következő adatlap"> &gt; </div>
            </div>
            <div className="container" style={{"padding-bottom": "10px"}}>
              <div className="row">
                <div className="col-lg-6 col-md-12 col-sm-12 col-xs-12 dogInfo" id="printThis1">
                  <p className="h3">Lucas</p>
                  <table>
                    <tbody>
                      <tr><td className="main">Státusz:</td> <td className="sub">Örökbe fogadható</td></tr>
                      <tr><td className="main">Fajta:</td>   <td className="sub">Keverék</td></tr>
                      <tr><td className="main">Ivar:</td>    <td className="sub">Kan</td></tr>
                      <tr><td className="main">Szőrzet:</td> <td className="sub">Sima</td></tr>
                      <tr><td className="main">Színezet:</td><td className="sub">Fekete</td></tr>
                      <tr><td className="main">Magasság:</td><td className="sub">Nagy</td></tr>
                      <tr><td className="main">Kor:</td>     <td className="sub">4 év</td></tr>                                            
                    </tbody>
                  </table>
                  <p className="description">
                    magyar leírás
                  </p>
                </div>
                <div className="col-lg-6 col-md-12 col-sm-12 col-xs-12 dogInfoColumn2">
                  <div className="col-lg-12 col-md-12 col-sm-12 col-xs-12 dogInfo dogInfo2">                                        
                    <div className="dogAttributes self-align-center">
                      <div className="row align-items-center attributeRow">
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute1">Nyugis</p>
                        <div className="col-lg-6 col-md-6 col-4 col-sm-6 col-3 align-middle attribute" style={{"background-color": "rgb(170, 170, 170)", "height": "10px", "width": "100%"}}>
                          <div style={{"position": "absolute", "left": "0", "background-color": "var(--main-pink-color)", "height": "10px", "width": "40%"}}>
                          </div>
                        </div>
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute2">Pörgős</p>
                      </div>
                      <div className="row align-items-center attributeRow">
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute1">Tompa</p>
                        <div className="col-lg-6 col-md-6 col-4 col-sm-6 col-3 align-middle attribute" style={{"background-color": "rgb(170, 170, 170)", "height": "10px", "width": "100%"}}>
                          <div style={{"position": "absolute", "left": "0", "background-color": "var(--main-pink-color)", "height": "10px", "width": "40%"}}>
                          </div>
                        </div>
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute2">Jól tanítható</p>
                      </div>
                      <div className="row align-items-center attributeRow">
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute1">Öntörvényű</p>
                        <div className="col-lg-6 col-md-6 col-4 col-sm-6 col-3 align-middle attribute" style={{"background-color": "rgb(170, 170, 170)", "height": "10px", "width": "100%"}}>
                          <div style={{"position": "absolute", "left": "0", "background-color": "var(--main-pink-color)", "height": "10px", "width": "40%"}}>
                          </div>
                        </div>
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute2">Engedelmes</p>
                      </div>
                      <div className="row align-items-center attributeRow">
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute1">Bizalmatlan</p>
                        <div className="col-lg-6 col-md-6 col-4 col-sm-6 col-3 align-middle attribute" style={{"background-color": "rgb(170, 170, 170)", "height": "10px", "width": "100%"}}>
                          <div style={{"position": "absolute", "left": "0", "background-color": "var(--main-pink-color)", "height": "10px", "width": "60%"}}>
                          </div>
                        </div>
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute2">Emberimádó</p>
                      </div>
                      <div className="row align-items-center attributeRow">
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute1">Önálló</p>
                        <div className="col-lg-6 col-md-6 col-4 col-sm-6 col-3 align-middle attribute" style={{"background-color": "rgb(170, 170, 170)", "height": "10px", "width": "100%"}}>
                          <div style={{"position": "absolute", "left": "0", "background-color": "var(--main-pink-color)", "height": "10px", "width": "100%"}}>
                          </div>
                        </div>
                        <p className="col-lg-3 col-md-3 col-sm-3 col-4 attribute2">Társfüggő</p>
                      </div>                                            
                    </div> 
                  </div>
                  <div className="w-100" style={{"height": "0px"}}></div>
                  <div className="col-lg-12 col-md-12 col-sm-12 col-xs-12 dogInfo dogInfoRightBottom">
                    <div className="col-10 favouriteDog" value="1" id="myFavouriteInner1" onclick="addToFavourites(this);">
                      <i className="fa fa-star"></i>Kedvencek közé rakom
                    </div>
                    <div className="col-10 supportDog">
                      <a href="./paypal_express_checkout/index_donation.php?dogID=1">
                        <i className="fas fa-drumstick-bite"></i>Virtuális gazdája leszek
                      </a>
                    </div>
                    <div className="row">
                      <div className="col-6 shareAndPrint align-self-center justify-content-center" data-href="http://pethodat.siofokiallatvedo.hu/viewer/" data-layout="button" data-toggle="tooltip" data-placement="top" title="Megosztom Facebookon">
                        <a target="_blank" rel="noreferrer" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fpethodat.siofokiallatvedo.hu%2Fviewer%2F&amp;src=sdkpreparse" className="fb-xfbml-parse-ignore fbc-has-badge fbc-UID_2">
                          <p className="shareAndPrintParagraph">
                            <i className="fab fa-facebook"></i>Megosztás
                          </p>
                        </a>
                      </div>
                      <div className="col-6 shareAndPrint align-self-center justify-content-center" id="btnPrint" onclick="printElement(document.getElementById('printThis1'))" data-toggle="tooltip" data-placement="top" title="Adatlap nyomtatása" value="1">
                        <p className="shareAndPrintParagraph">
                          <i className="fa fa-print"></i>Nyomtatás
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

function DogCard(props) {
  return (
    <div className="col-lg-2 col-md-3 col-sm-4 col-6 dogCard">
      <div className="col dogProfileImage" style={{"background-image": "url('http://pethodat.siofokiallatvedo.hu/pictures/" + props.dog.id.toString() + ".jpg')"}}>
        <div className="dogBar">
          <a href="#modal" className="open-button h4" data-toggle="modal" data-target="#dogProfileModal1">{props.dog.name}</a>
          <div className="row justify-content-center">
            <div className="col-12 dogIcons">
              <a href="#modal" data-toggle="modal" data-target="#dogProfileModal1">
                <i className="fas fa-paw" data-toggle="tooltip" data-placement="top" title="Adatlap"></i>
              </a>
              <a target="_blank" rel="noreferrer" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fpethodat.siofokiallatvedo.hu%2Fviewer%2F&amp;src=sdkpreparse" className="fb-xfbml-parse-ignore fbc-has-badge fbc-UID_1">
                <i className="fab fa-facebook" data-toggle="tooltip" data-placement="top" title="Megosztás"></i>
              </a>
              <a href="./paypal_express_checkout/index_donation.php?dogID=1">
                <i className="fas fa-drumstick-bite" data-toggle="tooltip" data-placement="top" title="Virtuális gazdája leszek"></i>
              </a>
              <a href="#addtofavourites" value="1" id="myFavourite1" onclick="addToFavourites(this);">
                <i className="fas fa-star" data-toggle="tooltip" data-placement="top" title="Kedvencek közé rakom"></i>
                <div className="popup"> 
                  <span className="popuptext" id="popup1">Hozzáadva a kedvencekhez    
                  </span>
                </div>
              </a>
            </div>
          </div>
        </div>
      </div>
      <DogModal dog={props.dog} />
    </div>
  )
}

function SearchPage() {

  const [isLoading, setIsLoading] = useState(false)
  const [error,     setError]     = useState(null)
  const [dogs,      setDogs]      = useState([])

  function loadDogs() {
    setIsLoading(true)
    axios
      .get('/search/listalldogs')
      .then(
        (result) => {
          setIsLoading(false)
          setDogs(result.data)
        },
        (error) => {
          setIsLoading(false)
          setError(error)
        })
  }

  useEffect(() => loadDogs(), [])

  function renderPage(content) {
    return (
      <>
        <SearchHeader />
        <SearchParameters />
        {content}
      </>
    )
  }

  if (error) {
    return renderPage(<div>Error: {error.message}</div>)
  } else if (isLoading) {
    return renderPage(<div>Loading...</div>)
  } else {
    return renderPage(
      <div className="row dogList" id="dogShow">
        {dogs.map(dog => (
          <DogCard dog={dog} />  
        ))}
      </div>
    )
  }

}

export default SearchPage
