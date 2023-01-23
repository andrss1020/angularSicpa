import _ from 'lodash';

export function getTotalItemsCreated(
  searchForm: any,
  searchFormDefault: any,
  totalItems: number,
  totalItemsCreated: number,
  isCreating: boolean
): number {
  totalItemsCreated = totalItems > totalItemsCreated ? totalItems : totalItemsCreated;
  if (!_.isEqual(searchForm, searchFormDefault)) {
    return totalItems <= totalItemsCreated && isCreating ? totalItemsCreated + 1 : totalItemsCreated;
  } else {
    return totalItems;
  }
}

export function getNumPage(totalItemsCreated: number, itemsPerPage: number): number {
  const numPage = totalItemsCreated / itemsPerPage - (totalItemsCreated % itemsPerPage) / itemsPerPage;
  return totalItemsCreated % itemsPerPage === 0 ? numPage : numPage + 1;
}
