import React, { Suspense } from 'react'
import ReactDOM from 'react-dom'
import './all.css'
import './styleLess.css'

const SearchPage = React.lazy(() => import('./SearchPage'))

ReactDOM.render(
  <React.StrictMode>
    <Suspense fallback={<div>Loading...</div>}>
      <SearchPage />
    </Suspense>
  </React.StrictMode>,
  document.getElementById('root')
)
