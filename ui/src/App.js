import React from 'react';
import HelloWorld from './components/HelloWorld'
import Header from './components/Header'
import Footer from './components/Footer'

function App() {

    return (
        <div className="App">
            <Header/>

            <HelloWorld
                caption="caption from main component"
                text="Hey ho from main file!"/>

            <Footer/>
        </div>
    );
}

export default App;
