import React from 'react'
// import logo from "../logo.svg";

class Main extends React.Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    {/*<img src={logo} className="App-logo" alt="logo"/>*/}
                    <p>
                        <p>Here is caption: {this.props.caption}</p>
                        <p>{this.props.text} =)</p>
                    </p>
                </header>
            </div>
        );
    }
}

export default Main