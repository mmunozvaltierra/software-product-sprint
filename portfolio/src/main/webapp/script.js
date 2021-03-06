// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

//Creates listener for 'Check Messages' button
window.onload=function(){
    var mb = document.getElementById("submit-retrieve");
    mb.addEventListener("click", this.loadMessages);
}

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['My favorite sports team is the Angels!','General Kenobi...you are a bold one', 'I enjoy watching Star Wars', 
        'I have a fear of spiders. I just cant deal with them','One of my favorite flavors is peanut butter!',
        'I love all types of fruits but apples are my favorite'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

async function fetchRandomMessage() {
    const serverResponse = await fetch('/hello');
    const responseText = await serverResponse.json();
    contentText = responseText[Math.floor(Math.random() * 3)];

    const responseContainer = document.getElementById('response-container');
    responseContainer.innerText = contentText;
}

/** Fetches messages from datastore and adds them to the DOM. */
function loadMessages() {
  fetch('/list-messages').then(response => response.json()).then((messages) => {
    const messageListElement = document.getElementById('message-list');
    messages.forEach((message) => {
      messageListElement.appendChild(createMessageElement(message));
    })
  });
}

/** Creates an element that represents a message */
function createMessageElement(message) {
  const messageElement = document.createElement('li');
  messageElement.className = 'task';

  const titleElement = document.createElement('span');
  titleElement.innerText = message;

  messageElement.appendChild(titleElement);
  return messageElement;
}