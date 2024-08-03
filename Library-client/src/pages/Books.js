import React, { useEffect, useState } from "react";
import api from "../components/ApiInterceptor";
import DataTable from "react-data-table-component";
import Button from "../components/Button";
import Modal from "../components/Modal";
import Input from "../components/Input";
import { bookFormFields } from "../constants/FormFields";
import { Form, useForm } from "react-hook-form";

function Books() {
  const [bookList, setBookList] = useState([]);

  const [isOpen, setIsOpen] = useState(false);

  const fields = bookFormFields;

  const [selectedRow, setSelectedRow] = useState();

  const { control, register, handleSubmit } = useForm({
    defaultValues: {
      bookTitle: "Java",
      authorName: "marikannan",
      publicationDate: "",
      isbnNumber: "",
    },
  });

  // useEffect(() => {
  //   if (selectedRow != null) {
  //     reset(selectedRow);
  //   }
  // }, [reset]);

  const fetchBookList = async () => {
    try {
      const response = await api.get("/book/allBooks");
      setBookList(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchBookList();
  }, []);

  const columns = [
    {
      name: "Book ID",
      selector: (row) => row.bookId,
      sortable: true,
      width: "8rem",
    },
    {
      name: "Title",
      selector: (row) => row.title,
      sortable: true,
      width: "8rem",
    },
    {
      name: "Author name",
      selector: (row) => row.authorName,
      sortable: true,
      width: "8rem",
    },
    {
      name: "Publication Date",
      selector: (row) => row.publicationDate,
      sortable: true,
      width: "12rem",
    },
    {
      name: "ISBN Number",
      selector: (row) => row.isbn,
      sortable: true,
      width: "12rem",
    },
    {
      name: "Action",
      button: true,
      width: "15rem",
      cell: (row) => {
        return (
          <div className="flex gap-1">
            <Button
              name="Update"
              isModal={true}
              setIsOpen={setIsOpen}
              row={row}
              setSelectedRow={setSelectedRow}
            />
            <Button name="Request" isModal={false} />
          </div>
        );
      },
    },
  ];
  console.log({ selectedRow });
  return (
    <>
      <div className="container flex mx-auto mt-12">
        <div className="mx-auto w-70%">
          <DataTable columns={columns} data={bookList} pagination></DataTable>
        </div>
        <div className="mx-auto w-20%"></div>
      </div>
      <Modal open={isOpen} onClose={() => setIsOpen(false)}>
        <div className="text-center w-56">
          <div className="mx-auto my-4 w-48">
            <Form control={control} onSubmit={handleSubmit((data) => {})}>
              {fields.map((field) => (
                <Input
                  {...register(field.id)}
                  key={field.id}
                  labelText={field.labelText}
                  labelFor={field.labelFor}
                  id={field.id}
                  name={field.name}
                  type={field.type}
                  isRequired={field.isRequired}
                  placeholder={field.placeholder}
                  showlabel={true}
                />
              ))}
              <div className="flex gap-4">
                <button className="btn btn-danger w-full">Delete</button>
                <button
                  className="btn btn-light w-full"
                  onClick={() => setIsOpen(false)}
                >
                  Cancel
                </button>
              </div>
            </Form>
          </div>

          {/* 
            <h3 className="text-lg font-black text-gray-800">Confirm Delete</h3>
            <p className="text-sm text-gray-500">
              Are you sure you want to delete this item?
            </p>
          */}
        </div>
      </Modal>
    </>
  );
}

export default Books;
