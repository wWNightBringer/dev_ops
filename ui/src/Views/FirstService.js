import React, {useState, useEffect} from 'react'
import axios from 'axios'

function FirstService() {
    const url = 'http://localhost:8081/api/shop-service';
    const [data, setData] = useState(null);
    let content = null;

    useEffect(() => {
        axios.get(url).then(response => setData(response.data.SystemInfoVO));
    }, [url]);

    if (data) {
        content =
            <div>
                <h1 className="font-bold text-2xl">Name {data.userName}</h1>
            </div>
    }

    return (
        <div>
            {content}
        </div>
    )
}

export default FirstService