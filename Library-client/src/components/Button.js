import React from "react";

const Button = (props) => {


  const handleModal = (row) => {
    props.setIsOpen(true)
    props.setSelectedRow(row);
    console.log(row)
  };

  return (
    <div>
      <button
        type="button"
        class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 font-samll rounded-lg text-sm px-3 py-2 mx-auto dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        onClick={() => (props.isModal ? handleModal(props.row) : null)}
      >
        {props.name}
      </button>
    </div>
  );
};

export default Button;
