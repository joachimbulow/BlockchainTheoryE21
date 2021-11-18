/*
 * Copyright IBM Corp. All Rights Reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

'use strict';

// Deterministic JSON.stringify()
const stringify  = require('json-stringify-deterministic');
const sortKeysRecursive  = require('sort-keys-recursive');
const { Contract } = require('fabric-contract-api');
class ChuckNorrisJokes extends Contract {

    async InitLedger(ctx) {
        const jokes = [
            {
                id: '1',
                joke: 'Chuck Norris doesn’t read books. He stares them down until he gets the information he wants.'
            },
            {
                id: '2',
                joke: 'Time waits for no man. Unless that man is Chuck Norris.',
               
            },
            {
                id: '3',
                joke: 'if you spell Chuck Norris in Scrabble, you win. Forever.',
            },
            {
                id: '4',
                joke: 'Chuck Norris breathes air … five times a day.',
            },
            {
                id: '5',
                joke: 'In the Beginning there was nothing … then Chuck Norris roundhouse kicked nothing and told it to get a job.',
            },
            {
                id: '6',
                joke: 'When God said, “Let there be light!” Chuck said, “Say Please.”',
            },
        ];

        for (const joke of jokes) {
            joke.docType = 'joke';
            await ctx.stub.putState(joke.id, Buffer.from(stringify(sortKeysRecursive(joke))));
        }
    }

    async getRandomJoke(ctx) {
        const allJokes = [];
        // range query with empty string for startKey and endKey does an open-ended query of all assets in the chaincode namespace.
        const iterator = await ctx.stub.getStateByRange('', '');
        let result = await iterator.next();
        while (!result.done) {
            const strValue = Buffer.from(result.value.value.toString()).toString('utf8');
            let record;
            try {
                record = JSON.parse(strValue);
            } catch (err) {
                console.log(err);
                record = strValue;
            }
            allJokes.push(record);
            result = await iterator.next();
        }
        return JSON.stringify(allJokes[Math.floor(Math.random() * allJokes.length)]);
    }

    
}

module.exports = ChuckNorrisJokes;
